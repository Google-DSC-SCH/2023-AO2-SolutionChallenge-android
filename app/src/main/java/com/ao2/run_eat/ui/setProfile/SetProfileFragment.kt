package com.ao2.run_eat.ui.setProfile

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentSetProfileBinding
import com.ao2.run_eat.ui.setProfile.bottom.BottomAgeNumberPicker
import com.ao2.run_eat.ui.setProfile.bottom.EditProfileImageBottomSheet
import com.ao2.run_eat.util.hideKeyboard
import com.ao2.run_eat.util.uriToFile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class SetProfileFragment :
    BaseFragment<FragmentSetProfileBinding, SetProfileViewModel>(R.layout.fragment_set_profile) {

    private val TAG = "SetProfileFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_set_profile

    override val viewModel: SetProfileViewModel by viewModels()
    private val navController by lazy { findNavController() }

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    private var cameraUri: Uri? = null

    // 요청하고자 하는 권한들
    private val permissionList = arrayOf(
        Manifest.permission.CAMERA
    )

    // 권한을 허용하도록 요청
    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            results.forEach {
                if (!it.value) toastMessage("권한 허용 필요")
            }
        }

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initEditText()
        initRegisterForActivityResult()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigationHandler.collectLatest {
                when (it) {
                    is SetProfileNavigationAction.NavigateToSetProfileImage -> {
                        editProfileImageBottomSheet()
                    }
                    is SetProfileNavigationAction.NavigateToHome -> navigate(SetProfileFragmentDirections.actionSetProfileFragmentToHomeFragment()
                    )
                    is SetProfileNavigationAction.NavigateToEmpty -> toastMessage("닉네임이 비어 있습니다!")
                    is SetProfileNavigationAction.NavigateToAgeNumberPicker -> ageNumberPicker()
                }
            }
        }
    }

    override fun initAfterBinding() {}

    private fun initRegisterForActivityResult() {
        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                activityResult.data?.let {
                    createFile(it.data!!)
                }
            }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                cameraUri?.let { uri ->
                    createFile(uri)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEditText() {
        //포커싱 시 검정 테두리 필요할 시 주석 해제
        //binding.userNameContents.customOnFocusChangeListener(requireContext())
        binding.profileSetMain.setOnTouchListener { _, _ ->
            requireActivity().hideKeyboard()
            binding.userNameContents.clearFocus()
            binding.ageContents.clearFocus()

            false
        }
    }

    private fun editProfileImageBottomSheet() {
        requestMultiplePermission.launch(permissionList)
        val dialog = EditProfileImageBottomSheet {
            if (it) getGalleryImage()
            else getCaptureImage()

            lifecycleScope.launch {
//                viewModel.editPossibleState.emit(true)
            }
        }
        dialog.show(childFragmentManager, TAG)
    }

    private fun getGalleryImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
//        viewModel.isGalleryImage.value = true
        galleryLauncher.launch(intent)

    }

    private fun getCaptureImage() {
//        viewModel.isGalleryImage.value = false
        cameraUri = createImageFile()
        cameraLauncher.launch(cameraUri)
    }

    private fun createImageFile(): Uri? {
        val now = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val content = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$now.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        return requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            content
        )
    }

    private fun createFile(uri: Uri) {
        val file = uriToFile(uri, requireContext())
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val requestBody = MultipartBody.Part.createFormData("file", file.name, requestFile)
        // Update Profile API
        viewModel.setFileToUri(file = requestBody)
    }

    /**
     * 날짜 선택하는 바텀 시트를 개발했지만 플로우가 긴 느낌이 들어서 일단 보류
     * */
    private fun ageNumberPicker() {
        val bottomSheet = BottomAgeNumberPicker(callback = {
            toastMessage(it.toString())
        })
        bottomSheet.show(requireActivity().supportFragmentManager, TAG)
    }
}
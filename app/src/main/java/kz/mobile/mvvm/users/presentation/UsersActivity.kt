package kz.mobile.mvvm.users.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.mobile.mvvm.R
import kz.mobile.mvvm.users.adapter.UsersAdapter
import kz.mobile.mvvm.users.domain.models.User
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EMPTY_STRING = ""
const val IMAGE_PICK_CODE = 999

class UsersActivity : AppCompatActivity() {

    private val usersViewModel: UsersViewModel by viewModel()
    private var viewManager = LinearLayoutManager(this)
    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var userAddButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var uploadImageView: ImageView
    private lateinit var chosenImage: ImageView
    private var chosenImageUri: Uri? = null
    private var usersAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)
        initViews()
        uploadImageView.setOnClickListener {
            launchGallery()
        }
        initializeAdapter()
        observeViewModel()
        initClickListeners()
    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            if (uri != null) {
                chosenImage.setImageURI(uri)
                chosenImageUri = uri
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initViews() {
        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        userAddButton = findViewById(R.id.button_add_user)
        nameEditText = findViewById(R.id.editText_name)
        surnameEditText = findViewById(R.id.editText_surname)
        uploadImageView = findViewById(R.id.uploadImageView)
        chosenImage = findViewById(R.id.chosenImage)
    }

    private fun initializeAdapter() {
        usersRecyclerView.layoutManager = viewManager
        usersAdapter = UsersAdapter()
        usersRecyclerView.adapter = usersAdapter
    }

    private fun observeViewModel() {
        usersViewModel.getUsersLiveData().observe(this, Observer { users ->
            usersAdapter?.setUsers(users as MutableList<User>)
            usersAdapter?.notifyDataSetChanged()
        })
    }

    private fun initClickListeners() {
        userAddButton.setOnClickListener {
            addUser()
            clearEditText()
            usersViewModel.getUsers()
        }
    }

    private fun addUser() {
        if(chosenImageUri == null) return

        val nameText: String = nameEditText.text.toString()
        val surnameText: String = surnameEditText.text.toString()
        val chosenImageUri : Uri = chosenImageUri as Uri
        val user = User(
            name = nameText,
            surname = surnameText,
            image = chosenImageUri
        )
        usersViewModel.addUser(user)
        usersRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun clearEditText() {
        nameEditText.setText(EMPTY_STRING)
        surnameEditText.setText(EMPTY_STRING)
        chosenImage.setImageResource(0)
    }
}
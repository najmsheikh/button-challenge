package me.najmsheikh.buttonchallenge;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import me.najmsheikh.buttonchallenge.models.User;


/**
 * A dialog Fragment that aids in adding a new {@link User}
 */
public class UserDialogFragment extends DialogFragment {

    private View mRootView;
    private Button mAddButton;
    private Button mCancelButton;
    private EditText mNameEditText;
    private EditText mEmailEditText;

    public UserDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize views
        mRootView = inflater.inflate(R.layout.fragment_user_dialog, container, false);
        mAddButton = mRootView.findViewById(R.id.button_add);
        mCancelButton = mRootView.findViewById(R.id.button_cancel);
        mNameEditText = mRootView.findViewById(R.id.et_user_name);
        mEmailEditText = mRootView.findViewById(R.id.et_user_email);

        // Handle clicking the "Add User" button in the dialog
        mAddButton.setOnClickListener(v -> {
            // Make sure email is in correct format before proceeding
            if (!isEmailValid(mEmailEditText.getText())) {
                Snackbar.make(mRootView, getString(R.string.error_dialog_email), Snackbar.LENGTH_LONG).show();
                return;
            }

            String name = mNameEditText.getText().toString();
            String email = mEmailEditText.getText().toString();

            ((MainActivity) getActivity()).addUser(new User(name, email));
            mNameEditText.getText().clear();
            mEmailEditText.getText().clear();
            dismiss();
        });

        // Handle clicking the "Cancel" button in the dialog
        mCancelButton.setOnClickListener(v -> dismiss());

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Ensure proper dialog dimensions
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private boolean isEmailValid(CharSequence email) {
        if (email == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

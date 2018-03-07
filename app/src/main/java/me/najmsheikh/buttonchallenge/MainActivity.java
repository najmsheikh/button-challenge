package me.najmsheikh.buttonchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.najmsheikh.buttonchallenge.adapters.UserAdapter;
import me.najmsheikh.buttonchallenge.models.User;
import me.najmsheikh.buttonchallenge.network.ButtonClient;
import me.najmsheikh.buttonchallenge.network.RetrofitServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter mAdapter;
    private List<User> mUserList;
    private ButtonClient mButtonClient;

    private View mRootView;
    private RecyclerView mUserRecyclerView;
    private FloatingActionButton mFab;
    private UserDialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        mRootView = findViewById(R.id.container);
        mUserRecyclerView = findViewById(R.id.rv_userlist);
        mFab = findViewById(R.id.fab_adduser);
        mDialog = new UserDialogFragment();

        // Setup view to show all retrieved Users
        mAdapter = new UserAdapter(new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserSelected(User user) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            }

            @Override
            public void onUserDeleted(User user) {
                deleteUser(user);
            }
        });
        mUserRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(mUserList);

        // Initialize Retrofit service to connect to the Fake-Button API
        mButtonClient = RetrofitServiceGenerator.createService(ButtonClient.class);

        // Retrieve all Users and update UI
        getUsers();

        // Handle opening a dialog to add a new User
        mFab.setOnClickListener(v -> mDialog.show(getSupportFragmentManager(), "UserDialogFragment"));
    }

    /**
     * Retrieves all the {@link User} and updates UI accordingly
     */
    private void getUsers() {
        Call<List<User>> call = mButtonClient.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    mUserList = response.body();
                    mAdapter.setList(mUserList);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Snackbar.make(mRootView, getString(R.string.error_userlist_network), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Delete a given {@link User} and update UI accordingly
     *
     * @param user The User to be deleted
     */
    private void deleteUser(User user) {
        String id = String.valueOf(user.getId());

        Call<Void> call = mButtonClient.deleteUser(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Snackbar.make(mRootView, getString(R.string.message_userlist_userdeleted), Snackbar.LENGTH_SHORT).show();
                    mAdapter.deleteUser(user);
                } else {
                    Snackbar.make(mRootView, getString(R.string.error_userlist_delete), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Snackbar.make(mRootView, getString(R.string.error_userlist_network), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Add a given {@link User} and update the UI accordingly
     *
     * @param user The User to be added
     */
    protected void addUser(User user) {
        Call<User> call = mButtonClient.addUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    Snackbar.make(mRootView, getString(R.string.message_userlist_useradded), Snackbar.LENGTH_SHORT).show();
                    mAdapter.addUser(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Snackbar.make(mRootView, getString(R.string.error_userlist_network), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}

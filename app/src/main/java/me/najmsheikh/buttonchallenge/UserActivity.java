package me.najmsheikh.buttonchallenge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import me.najmsheikh.buttonchallenge.adapters.TransferAdapter;
import me.najmsheikh.buttonchallenge.models.Transfer;
import me.najmsheikh.buttonchallenge.models.User;
import me.najmsheikh.buttonchallenge.network.ButtonClient;
import me.najmsheikh.buttonchallenge.network.RetrofitServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private ButtonClient mButtonClient;
    private TransferAdapter mAdapter;
    private RecyclerView mTransferRecyclerView;
    private User mUser;

    private View mRootView;
    private TextView mUserName;
    private TextView mUserEmail;
    private TextView mUserTransCount;
    private FloatingActionButton mFab;
    private TransferDialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Initialize views
        mRootView = findViewById(R.id.container);
        mUserName = findViewById(R.id.et_user_name);
        mUserEmail = findViewById(R.id.et_user_email);
        mUserTransCount = findViewById(R.id.tv_user_transcount);
        mFab = findViewById(R.id.fab_addtransfer);
        mDialog = new TransferDialogFragment();
        mTransferRecyclerView = findViewById(R.id.rv_transfers);

        // Initialize core elements
        mButtonClient = RetrofitServiceGenerator.createService(ButtonClient.class);
        mAdapter = new TransferAdapter();
        mTransferRecyclerView.setAdapter(mAdapter);

        // Retrieve User object from caller Activity via the Intent
        mUser = getIntent().getParcelableExtra("EXTRA_USER");

        // If User is successfully retrieved, get its Transfers and update the UI
        if (mUser != null) {
            getSupportActionBar().setTitle(mUser.getName());
            mUserName.setText(mUser.getName());
            mUserEmail.setText(mUser.getEmail());

            getTransfers(mUser.getId());
        } else {
            finish();
        }

        // Handle opening a dialog to initiate a Transfer
        mFab.setOnClickListener(v -> mDialog.show(getSupportFragmentManager(), "TransferDialogFragment"));
    }

    /**
     * Adds a {@link Transfer} and refreshes the list of Transfers for the current user.
     *
     * @param transfer
     * @see #getTransfers(Long)
     */
    protected void addTransfer(Transfer transfer) {
        Call<Transfer> call = mButtonClient.addTransfer(transfer);
        call.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if (response.body() != null) {
                    getTransfers(mUser.getId());
                    Snackbar.make(mRootView, getString(R.string.message_transfer_added), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {
                Snackbar.make(mRootView, getString(R.string.error_userlist_network), Snackbar.LENGTH_LONG).show();
            }
        });

    }

    /**
     * Retrieves all the {@link Transfer} for the current user and updates the UI accordingly.
     *
     * @param id
     */
    private void getTransfers(Long id) {
        Call<List<Transfer>> call = mButtonClient.getTransfers(String.valueOf(id));
        call.enqueue(new Callback<List<Transfer>>() {
            @Override
            public void onResponse(Call<List<Transfer>> call, Response<List<Transfer>> response) {
                if (response.body() != null) {
                    mAdapter.setList(response.body());
                    mUserTransCount.setText(String.valueOf(response.body().size()));
                }
            }

            @Override
            public void onFailure(Call<List<Transfer>> call, Throwable t) {
                Snackbar.make(mRootView, getString(R.string.error_userlist_network), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}

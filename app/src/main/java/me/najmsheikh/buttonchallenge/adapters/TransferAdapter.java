package me.najmsheikh.buttonchallenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.najmsheikh.buttonchallenge.R;
import me.najmsheikh.buttonchallenge.models.Transfer;

/**
 * Created by Najm Sheikh <hello@najmsheikh.me> on 3/5/18.
 */

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> {

    private List<Transfer> mTransferList;

    public TransferAdapter() {

    }

    @NonNull
    @Override
    public TransferAdapter.TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transfer, parent, false);
        return new TransferViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.TransferViewHolder holder, int position) {
        holder.bind(mTransferList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTransferList != null ? mTransferList.size() : 0;
    }

    public void setList(List<Transfer> transferList) {
        mTransferList = transferList;
        notifyDataSetChanged();
    }

    public void addTransfer(Transfer transfer) {
        mTransferList.add(transfer);
        notifyDataSetChanged();
    }

    static class TransferViewHolder extends RecyclerView.ViewHolder {

        TextView mTransferTextView;

        TransferViewHolder(View itemView) {
            super(itemView);
            mTransferTextView = itemView.findViewById(R.id.tv_transfer);
        }

        void bind(final Transfer transfer) {
            String amount = transfer.getAmount();

            String message = String.format("You received $%s!", amount);

            mTransferTextView.setText(message);
        }
    }
}

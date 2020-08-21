package com.example.smartenergymonitoring.expense;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.database.SQLHelper;
import com.example.smartenergymonitoring.models.ExpenseModel;

import java.util.ArrayList;

public class ExpenseItemAdapter extends RecyclerView.Adapter<ExpenseItemAdapter.MyViewHolder> {

    Context context;
    ArrayList<ExpenseModel> list;
    SQLHelper myDb;

    public ExpenseItemAdapter(Context context, ArrayList<ExpenseModel> list) {
        this.context = context;
        this.list = list;
        myDb = new SQLHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item_layout,parent,false);
        return new ExpenseItemAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final ExpenseModel expenseModel = list.get(i);
        holder.txtTitle.setText(expenseModel.getTitle());
        holder.txtDesc.setText("Amount: "+expenseModel.getAmount());
        holder.txtAmount.setText(expenseModel.getTitle().substring(0, 1));

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this Expense ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDb.deleteExpenseData(expenseModel.getId());
                        removeAt(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddExpenseScreen.class);
                intent.putExtra("mode","edit");
                intent.putExtra("model",expenseModel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDesc,txtAmount;
        ImageView imgDelete;
        ConstraintLayout frame;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            frame = itemView.findViewById(R.id.frame);
        }
    }

    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}

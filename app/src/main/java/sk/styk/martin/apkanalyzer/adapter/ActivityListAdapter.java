package sk.styk.martin.apkanalyzer.adapter;

import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import sk.styk.martin.apkanalyzer.R;
import sk.styk.martin.apkanalyzer.model.detail.ActivityData;
import sk.styk.martin.apkanalyzer.view.DetailListItemView;

/**
 * Created by Martin Styk on 07.07.2017.
 */
public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {

    private final List<ActivityData> items;

    public ActivityListAdapter(@NonNull List<ActivityData> items) {
        super();
        setHasStableIds(true);
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ActivityData data = items.get(position);
        holder.name.setText(data.getName());
        holder.label.setValue(data.getLabel());
        holder.parent.setValue(data.getParentName());
        holder.permission.setValue(data.getPermission());

        if (data.isExported()) {
            holder.run.setEnabled(true);
            holder.run.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(data.getPackageName(), data.getName()));
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            holder.run.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final DetailListItemView label;
        final DetailListItemView parent;
        final DetailListItemView permission;
        final Button run;

        ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.item_activity_name);
            label = (DetailListItemView) v.findViewById(R.id.item_activity_label);
            parent = (DetailListItemView) v.findViewById(R.id.item_activity_parent);
            permission = (DetailListItemView) v.findViewById(R.id.item_activity_permission);
            run = (Button) v.findViewById(R.id.item_activity_run);
        }
    }
}
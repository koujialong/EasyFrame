package com.spy.easyframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ItemListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
//    List<Item> items=new ArrayList<>();
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//        final ViewHolder viewHolder= (ViewHolder) holder;
//        final Item item=items.get(position);
//        Glide.with(holder.itemView.getContext()).load(item.imageUrl).into(viewHolder.imageIv);
//        viewHolder.descriptionTv.setText(item.description);
//        viewHolder.imageIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(viewHolder.imageIv.getContext(), ImageActivity.class);
//                intent.putExtra("image",item.imageUrl);
//                viewHolder.imageIv.getContext().startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return items==null?0:items.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder{
//        @Bind(R.id.imageIv)
//        ImageView imageIv;
//        @Bind(R.id.descriptionTv)
//        TextView descriptionTv;
//
//        public ViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
//
//    public void setItems(List<Item> items){
//        this.items.clear();
//        this.items.addAll(items);
//        notifyDataSetChanged();
//    }
//
//    public void addItems(List<Item> items){
//        this.items.addAll(items);
//        notifyDataSetChanged();
//    }
}

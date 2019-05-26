package social.tochka.android.construct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import social.tochka.android.R;
import social.tochka.android.construct.MerchListFragment.OnListFragmentInteractionListener;


public class MyMerchRecyclerViewAdapter extends RecyclerView.Adapter<MyMerchRecyclerViewAdapter.ViewHolder> {

    private final List<MerchItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMerchRecyclerViewAdapter(List<MerchItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_merch_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mMerchItemImageView.setImageBitmap(mValues.get(position).getBlackBitmap());
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
        //
        //holder.mView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        if (null != mListener) {
        //            // Notify the active callbacks interface (the activity, if the
        //            // fragment is attached to one) that an item has been selected.
        //            mListener.onListFragmentInteraction(holder.mItem);
        //        }
        //    }
        //});
    }

    @Override
    public int getItemCount() {
        return mValues.size();
        //return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mMerchItemImageView, mMerchItemSizeView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMerchItemImageView = view.findViewById(R.id.merch_item_image_view);
            mMerchItemSizeView = view.findViewById(R.id.merch_item_size_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "SASAK";
        }
    }
}

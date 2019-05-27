package social.tochka.android.construct;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
        holder.mMerchItemImageView.setImageBitmap(mValues.get(position).getBlackList().get(0));

        //holder.mBlackButton.setOnClickListener(buttonListener);
        //holder.mGreenButton.setOnClickListener(buttonListener);
        //holder.mGreyButton.setOnClickListener(buttonListener);
        //holder.mRedButton.setOnClickListener(buttonListener);

        setListener(position, holder.mBlackButton, holder.mMerchItemImageView);
        setListener(position, holder.mGreenButton, holder.mMerchItemImageView);
        setListener(position, holder.mGreyButton, holder.mMerchItemImageView);
        setListener(position, holder.mRedButton, holder.mMerchItemImageView);
        setListener(position, holder.mArrowView, holder.mMerchItemImageView);

        holder.mChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction("FUCK");
            }
        });
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
    }

    private void setListener(final int position, final ImageButton button, final ImageView imageView) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton viewButton = (ImageButton) v;
                switch (v.getId()) {
                    case R.id.black_button :
                        v.setBackgroundResource(R.drawable.circle_black_tapped);
                        imageView.setImageBitmap(mValues.get(position).getBlackList().get(0));
                        break;
                    case R.id.green_button :
                        v.setBackgroundResource(R.drawable.circle_green_tapped);
                        imageView.setImageBitmap(mValues.get(position).getGreenList().get(0));
                        break;
                    case R.id.grey_button :
                        v.setBackgroundResource(R.drawable.circle_grey_tapped);
                        imageView.setImageBitmap(mValues.get(position).getGreyList().get(0));
                        break;
                    case R.id.red_button :
                        v.setBackgroundResource(R.drawable.circle_red_tapped);
                        imageView.setImageBitmap(mValues.get(position).getRedList().get(0));
                        break;
                    case R.id.arrow_right_view :
                        if (position == 0) {
                            imageView.setImageBitmap(mValues.get(position).getBlackList().get(1));
                        } else {
                            imageView.setImageBitmap(mValues.get(position).getGreyList().get(1));
                        }
                        break;
                }
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView mMerchItemImageView, mMerchItemSizeView;
        public ImageButton mBlackButton, mGreenButton, mGreyButton, mRedButton, mArrowView, mChooseButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMerchItemImageView = view.findViewById(R.id.merch_item_image_view);
            mMerchItemSizeView = view.findViewById(R.id.merch_item_size_view);
            mBlackButton = view.findViewById(R.id.black_button);
            mGreenButton = view.findViewById(R.id.green_button);
            mGreyButton = view.findViewById(R.id.grey_button);
            mRedButton = view.findViewById(R.id.red_button);
            mArrowView = view.findViewById(R.id.arrow_right_view);
            mChooseButton = view.findViewById(R.id.choose_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "SASAK";
        }
    }
}

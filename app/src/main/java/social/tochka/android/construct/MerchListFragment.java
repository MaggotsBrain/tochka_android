package social.tochka.android.construct;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import social.tochka.android.R;
/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MerchListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MerchListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MerchListFragment newInstance(int columnCount) {
        MerchListFragment fragment = new MerchListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_merch_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            List<MerchItem> merchItemList = new ArrayList<>();

            Bitmap bitmap_black_one = BitmapFactory.decodeResource(getResources(), R.drawable.shopper_black);
            Bitmap bitmap_black_two = BitmapFactory.decodeResource(getResources(), R.drawable.shopper_black_2);

            Bitmap bitmap_white_one = BitmapFactory.decodeResource(getResources(),R.drawable.shopper_white_1);
            Bitmap bitmap_white_two = BitmapFactory.decodeResource(getResources(),R.drawable.shopper_white_2);

            MerchItem bufferMerchItem = new MerchItem();

            //
            List<Bitmap> blackListBuffer = new ArrayList<>();
            List<Bitmap> whiteListBuffer = new ArrayList<>();

            blackListBuffer.add(bitmap_black_one);
            blackListBuffer.add(bitmap_black_two);

            whiteListBuffer.add(bitmap_white_one);
            whiteListBuffer.add(bitmap_white_two);

            bufferMerchItem.setBlackList(blackListBuffer);
            bufferMerchItem.setGreyList(whiteListBuffer);

            merchItemList.add(bufferMerchItem);

            //
            List<Bitmap> blackListBuffer2 = new ArrayList<>();

            blackListBuffer2.add(bitmap_black_two);
            blackListBuffer2.add(bitmap_black_two);

            MerchItem bufferMerchItem2 = new MerchItem();

            bufferMerchItem2.setBlackList(blackListBuffer2);
            bufferMerchItem2.setGreyList(whiteListBuffer);

            merchItemList.add(bufferMerchItem2);

            recyclerView.setAdapter(new MyMerchRecyclerViewAdapter(merchItemList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String item);
    }
}

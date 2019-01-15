package com.mypig.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mypig.R;
import com.mypig.dog.MyImageView;

import static com.mypig.util.Constants.COMPRESS_QUALITY;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements MyImageView.TextListener {

    private TextView tvSize;
    private TextView tvSrcSize;
    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_a,container,false);
        TextView text = view.findViewById(R.id.text);
        text.setText("质量压缩");
        MyImageView imageView = view.findViewById(R.id.imageview);
        imageView.setListener(this);
        imageView.setCompressType(COMPRESS_QUALITY);
        tvSize = view.findViewById(R.id.size);
        tvSrcSize = view.findViewById(R.id.src_size);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void showText(String srcSize, String size) {
        tvSize.setText(size);
        tvSrcSize.setText(srcSize);
    }
}

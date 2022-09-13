package by.pmvs.tabsandswipe.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import by.pmvs.tabsandswipe.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FirstActivity extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public static FirstActivity newInstance(int index) {
        FirstActivity fragment = new FirstActivity();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.first_tab, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
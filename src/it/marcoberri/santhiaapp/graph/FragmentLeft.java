package it.marcoberri.santhiaapp.graph;



import it.marcoberri.santhiaapp.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLeft extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
    	return inflater.inflate(R.layout.left_frame, container, false);
    }
    
}
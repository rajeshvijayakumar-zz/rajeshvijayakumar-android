package org.rajeshvijayakumar.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {
	String[] names = new String[] { "Rajesh", "Mahesh", "Mrithula", "Sonika",
			"Ramachander", "Sriram", "Omji", "Raji" };
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_fragment, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, names);
		setListAdapter(adapter);
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		Fragment fragment = new TextFragment();
		
		Bundle bundle = new Bundle();
		bundle.putString("names", names[position]);
		fragment.setArguments(bundle);
		fragmentTransaction.add(R.id.fragment2, fragment, "Fragment");
		fragmentTransaction.commit();
		getListView().setSelector(android.R.color.holo_blue_dark);
	}
}
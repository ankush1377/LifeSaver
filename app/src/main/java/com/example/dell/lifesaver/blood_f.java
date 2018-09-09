package com.example.dell.lifesaver;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link blood_f.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link blood_f#newInstance} factory method to
 * create an instance of this fragment.
 */
public class blood_f extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText name;
    Spinner bg;
    Button fetch;
    DBHandler dbHandler;
    Cursor cursor;
    String n,e;

    public blood_f() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment blood_f.
     */
    // TODO: Rename and change types and number of parameters
    public static blood_f newInstance(String param1, String param2) {
        blood_f fragment = new blood_f();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blood_f, container, false);

        bg=(Spinner) view.findViewById(R.id.bg);
        fetch=(Button) view.findViewById(R.id.fetch);
        name=(EditText) view.findViewById(R.id.name);
        n=Main2Activity.sendName();
        e=Main2Activity.sendEmail();

        String[] blood={"Blood Group","A-","A+","B-","B+","AB-","AB+","O-","O+"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,blood);
        bg.setAdapter(arrayAdapter);

        fetch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(name.getText().toString().equals("")){
                            name.setError("Please enter your name");
                            name.requestFocus();
                        }
                        else if(bg.getSelectedItem().toString().equals("Blood Group"))
                        {
                            Toast.makeText(getContext(),"Please select a Blood Group",Toast.LENGTH_LONG).show();
                        }
                        else{

                            Boolean flag = false;
                            dbHandler = new DBHandler(getContext());
                            cursor = dbHandler.getDonors(dbHandler, bg.getSelectedItem().toString());
                            cursor.moveToFirst();

                            do {
                                if ((cursor.getString(2)).equals(bg.getSelectedItem().toString())) {
                                    flag = true;
                                    break;
                                }
                            } while (cursor.moveToNext());

                            if (flag) {

                                Intent i = new Intent(getContext(), Donor_List.class);
                                i.putExtra("BG", bg.getSelectedItem().toString());
                                i.putExtra("Name2",name.getText().toString());
                                i.putExtra("Name",n);
                                i.putExtra("Email",e);
                                startActivity(i);

                            }
                            else
                                Toast.makeText(getContext(),"Sorry!No user with this blood group found!",Toast.LENGTH_LONG).show();
                        }
                    }
    });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

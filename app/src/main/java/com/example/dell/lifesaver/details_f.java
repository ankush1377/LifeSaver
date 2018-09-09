package com.example.dell.lifesaver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.InputStream;


public class details_f extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText address,pn,ec,ed;
    Spinner bg;
    Button save;

    public details_f() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment details_f.
     */
    // TODO: Rename and change types and number of parameters
    public static details_f newInstance(String param1, String param2) {
        details_f fragment = new details_f();
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
        final View view=inflater.inflate(R.layout.fragment_details_f, container, false);

        address=(EditText) view.findViewById(R.id.address);
        pn=(EditText) view.findViewById(R.id.pn);
        ec=(EditText) view.findViewById(R.id.ec);
        ed=(EditText) view.findViewById(R.id.ed);
        bg=(Spinner) view.findViewById(R.id.bg);
        save=(Button) view.findViewById(R.id.save);

        String[] blood={"Blood Group","A-","A+","B-","B+","AB-","AB+","O-","O+"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,blood);
        bg.setAdapter(arrayAdapter);


        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if(address.getText().toString().equals("")){
                            address.setError("Enter address");
                            address.requestFocus();
                        }
                        else if(pn.getText().toString().equals("")){
                            pn.setError("Enter you contact number");
                            pn.requestFocus();
                        }
                        else if(bg.getSelectedItem().toString().equals("Blood Group")) {
                            Snackbar.make(view, "Select a Blood Group", Snackbar.LENGTH_LONG).show();
                        }
                        else if(ec.getText().toString().equals("")){
                            ec.setError("Enter emergency contact");
                            ec.requestFocus();
                        }
                        else if(ec.getText().toString().length()<10){
                            ec.setError("Invalid contact number!");
                            ec.requestFocus();
                        }
                        else if(ed.getText().toString().equals("")){
                            ec.setError("Enter emergency dial-up");
                            ec.requestFocus();
                        }
                        else if(ed.getText().toString().length()<3 || ed.getText().toString().length()>4){
                            ec.setError("Invalid dial-up!");
                            ec.requestFocus();
                        }
                        else{

                            DBHandler dbHandler=new DBHandler(getContext());
                            dbHandler.putData(dbHandler,Main2Activity.sendName(),Main2Activity.sendEmail(),address.getText().toString(),pn.getText().toString(),bg.getSelectedItem().toString(),
                                    ec.getText().toString(),ed.getText().toString());


                            address.setText("");
                            pn.setText("");
                            ec.setText("");
                            ed.setText("");
                            bg.setSelection(0);

                            Toast.makeText(getContext(),"Successfully Saved", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

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

package com.example.dell.lifesaver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link firstaid_f.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link firstaid_f#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstaid_f extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public firstaid_f() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firstaid_f.
     */
    // TODO: Rename and change types and number of parameters
    public static firstaid_f newInstance(String param1, String param2) {
        firstaid_f fragment = new firstaid_f();
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

        View v=  inflater.inflate(R.layout.fragment_firstaid_f, container, false);
        return v;
    }

    public void onClick(View v){

        int id = v.getId();

        if (id == R.id.button1) {
            startActivity(new Intent(getContext(), Bleeding.class));
        }
        else if (id == R.id.button2) {
            startActivity(new Intent(getContext(), Burn.class));
        }
        else if (id == R.id.button3) {
            startActivity(new Intent(getContext(), HeartAttack.class));
        }
        else if (id == R.id.button4) {
            startActivity(new Intent(getContext(), Choking.class));
        }
        else if (id == R.id.button5) {
            startActivity(new Intent(getContext(), HeatStroke.class));
        }
        else if (id == R.id.button6) {
            startActivity(new Intent(getContext(),Shock.class));
        }
        else if (id == R.id.button7) {
            startActivity(new Intent(getContext(),Asthma.class));
        }
        else if (id == R.id.button8) {
            startActivity(new Intent(getContext(),StrainsSprains.class));
        }
        else if (id == R.id.button9) {
            startActivity(new Intent(getContext(),Fever.class));
        }
        else if (id == R.id.button10) {
            startActivity(new Intent(getContext(),Unconscious.class));
        }
        else if (id == R.id.button11) {
            startActivity(new Intent(getContext(),Poison.class));
        }
        else if (id == R.id.button12) {
            startActivity(new Intent(getContext(),Bites.class));
        }
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

package com.example.parcialmoviles.ui.salir;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;

public class SalirFragment extends Fragment {

    private SalirViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        viewModel = new ViewModelProvider(this).get(SalirViewModel.class);


        viewModel.getSalir().observe(getViewLifecycleOwner(), salir -> {
            if (salir != null && salir) {
                requireActivity().finish(); // cerrar app
            }
        });


        new AlertDialog.Builder(requireContext())
                .setTitle("Salir")
                .setMessage("¿Desea cerrar la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> viewModel.confirmarSalir())
                .setNegativeButton("No", (dialog, which) -> viewModel.cancelarSalir())
                .show();


        return new View(requireContext());
    }
}

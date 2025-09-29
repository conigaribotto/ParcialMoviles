package com.example.parcialmoviles.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.parcialmoviles.R;
import com.example.parcialmoviles.ui.modelo.ProductosViewModel;

public class CargarFragment extends Fragment {

    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnAgregar;
    private ProductosViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cargar, container, false);

        etCodigo = root.findViewById(R.id.etCodigo);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etPrecio = root.findViewById(R.id.etPrecio);
        btnAgregar = root.findViewById(R.id.btnAgregar);

        // Obtenemos el ViewModel compartido
        viewModel = new ViewModelProvider(requireActivity()).get(ProductosViewModel.class);

        // Botón para agregar producto: solo envía los valores al ViewModel
        btnAgregar.setOnClickListener(v -> viewModel.agregarProducto(
                etCodigo.getText().toString(),
                etDescripcion.getText().toString(),
                etPrecio.getText().toString()
        ));

        // Observamos los mensajes del ViewModel
        viewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje == null || mensaje.isEmpty()) return;

            if (mensaje.equals("OK")) {
                Toast.makeText(getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
                etCodigo.setText("");
                etDescripcion.setText("");
                etPrecio.setText("");
            } else {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}


package com.example.parcialmoviles.ui.modificar;

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
import com.example.parcialmoviles.ui.modelo.Producto;
import com.example.parcialmoviles.ui.modelo.ProductosViewModel;

public class DetalleProductoFragment extends Fragment {

    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnGuardar;
    private ProductosViewModel productosViewModel;
    private Producto producto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        etCodigo = root.findViewById(R.id.etCodigo);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etPrecio = root.findViewById(R.id.etPrecio);
        btnGuardar = root.findViewById(R.id.btnGuardar);

        productosViewModel = new ViewModelProvider(requireActivity()).get(ProductosViewModel.class);

        if (getArguments() != null) {
            producto = (Producto) getArguments().getSerializable("producto");
            if (producto != null) {
                etCodigo.setText(producto.getCodigo());
                etDescripcion.setText(producto.getDescripcion());
                etPrecio.setText(String.valueOf(producto.getPrecio()));
            }
        }

        btnGuardar.setOnClickListener(v -> productosViewModel.modificarProducto(
                producto.getCodigo(),
                etCodigo.getText().toString(),
                etDescripcion.getText().toString(),
                etPrecio.getText().toString()
        ));

        productosViewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje.equals("OK") ? "Producto modificado" : mensaje,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}

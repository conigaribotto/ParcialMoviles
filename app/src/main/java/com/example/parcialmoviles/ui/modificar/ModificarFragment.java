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
import androidx.navigation.Navigation;

import com.example.parcialmoviles.R;
import com.example.parcialmoviles.ui.modelo.ProductosViewModel;

public class ModificarFragment extends Fragment {

    private EditText etCodigo;
    private Button btnBuscar;
    private ModificarViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_modificar, container, false);

        etCodigo = root.findViewById(R.id.etCodigo);
        btnBuscar = root.findViewById(R.id.btnBuscar);

        // Obtener ViewModel compartido de productos
        ProductosViewModel sharedViewModel = new ViewModelProvider(requireActivity())
                .get(ProductosViewModel.class);

        // Crear ViewModel de Modificar con referencia al compartido
        viewModel = new ModificarViewModel(sharedViewModel);

        btnBuscar.setOnClickListener(v -> viewModel.buscarProducto(etCodigo.getText().toString()));

        // Observador de producto encontrado
        viewModel.getProductoSeleccionado().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                Navigation.findNavController(root)
                        .navigate(R.id.nav_detalle_producto, bundle);
            }
        });

        // Observador de mensajes
        viewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}

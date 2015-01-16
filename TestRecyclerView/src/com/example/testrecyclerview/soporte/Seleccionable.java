package com.example.testrecyclerview.soporte;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;

/**
 * Created by Amura on 23/12/2014.
 */
public abstract class Seleccionable extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SparseBooleanArray seleccionados = new SparseBooleanArray();
    private ArrayList<Identificable> items = new ArrayList<Identificable>();

    public CustomActionMode am_eliminar;
    public ActionBarActivity activity;

    public boolean isSeleccionado(int id) {
        return seleccionados.get(id);
    }

    public void setSeleccionado(int id, boolean valor) {
        if (valor) {
            seleccionados.put(id, true);
            if (seleccionados.size() == 1) {
                iniciarSeleccion();
            }
        } else {
            seleccionados.delete(id);
            if (seleccionados.size() == 0) {
                finalizarSeleccion();
            }
        }

        am_eliminar.actualizarTitulo();

        int pos = getPosItem(id);
        notifyItemChanged(pos);
    }

    public void seleccionarTodo() {
        seleccionados.clear();
        for (int i = 0; i < items.size(); i++) {
            int id = items.get(i).getId();
            seleccionados.put(id, true);
        }
        am_eliminar.actualizarTitulo();
        notifyDataSetChanged();
    }

    public void quitarSelecciones() {
        Log.i("", "seleccionados: " + seleccionados.size());
        for (int i = 0; i < seleccionados.size(); i++) {
            int id = seleccionados.keyAt(i);
            int pos = getPosItem(id);
            seleccionados.put(id, false);
            notifyItemChanged(pos);
        }
        seleccionados.clear();
    }

    public void borrarSeleccionados() {
        for (int i = 0; i < seleccionados.size(); i++) {
            int id = seleccionados.keyAt(i);
            quitarItemId(id);
        }
        seleccionados.clear();
        finalizarSeleccion();
    }


    public void agregarItem(int position, Identificable e) {
        items.add(position, e);
        notifyItemInserted(position);
    }


    public void quitarItemId(int id) {
        int pos = getPosItem(id);
        if (pos == -1)
            return;
        quitarItemPos(pos);
    }

    public void quitarItemPos(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public int getPosItem(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (getItem(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Identificable getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    public int getCantidadSeleccionados() {
        return seleccionados.size();
    }

    public void iniciarSeleccion() {
        activity.startSupportActionMode(am_eliminar);
    }

    public void finalizarSeleccion() {
        am_eliminar.finalizar();
    }

    public boolean isSeleccionando() {
        return am_eliminar.actionMode != null;
    }


}

package com.example.testrecyclerview.soporte;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;

/**
 * Created by Amura on 23/12/2014.
 */
public abstract class CustomActionMode implements ActionMode.Callback {
    public ActionMode actionMode = null;
    int resource;
    Seleccionable selector;
    ActionBarActivity ac;

    public CustomActionMode(int resource, Seleccionable selector, ActionBarActivity ac) {
        this.resource = resource;
        this.selector = selector;
        this.ac = ac;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(resource, menu);
        this.actionMode = actionMode;
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        this.actionMode = null;
        selector.quitarSelecciones();
    }

    public void actualizarTitulo() {
        if (actionMode == null)
            return;

        actionMode.setTitle(selector.getCantidadSeleccionados() + " items seleccionados");
    }

    public void finalizar() {
        actionMode.finish();
    }

    public Seleccionable getSelector() {
        return selector;
    }
}

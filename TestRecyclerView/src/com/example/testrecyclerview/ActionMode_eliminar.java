package com.example.testrecyclerview;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.MenuItem;

import com.example.testrecyclerview.soporte.CustomActionMode;
import com.example.testrecyclerview.soporte.Seleccionable;

/**
 * Created by Amura on 23/12/2014.
 */

public class ActionMode_eliminar extends CustomActionMode {


    public ActionMode_eliminar(int resource, Seleccionable selector, ActionBarActivity ac) {
        super(resource, selector, ac);
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id)
        {
            case R.id.cab_borrar_borrar:
                getSelector().borrarSeleccionados();
                break;
            case R.id.cab_borrar_todo:
                getSelector().seleccionarTodo();
                break;
        }
        return false;
    }

}

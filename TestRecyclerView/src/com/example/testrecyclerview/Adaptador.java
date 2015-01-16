package com.example.testrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.testrecyclerview.soporte.Seleccionable;

/**
 * Created by Amura on 23/12/2014.
 */
public class Adaptador extends Seleccionable {

    public Adaptador(MainActivity actividad) {
        this.activity = actividad;
        this.am_eliminar = new ActionMode_eliminar(R.menu.cab_borrar, this, actividad);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        Elemento e = (Elemento) getItem(position);
        vh.lblTexto.setText(e.getTexto());

        int id = e.getId();
        vh.item.setActivated(isSeleccionado(id));
        
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View item;
        public TextView lblTexto;
        public CheckBox chb;
        
        public ViewHolder(View v) {
            super(v);
            item = v.findViewById(R.id.item);
            lblTexto = (TextView) v.findViewById(R.id.lblTexto);
            chb = (CheckBox) v.findViewById(R.id.chb);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSeleccionando()) {
                        int pos = getPosition();
                        int id = (int) Adaptador.this.getItemId(pos);
                        setSeleccionado(id, !isSeleccionado(id));
                    }
                    else
                    {
                    	Elemento e = (Elemento)getItem(getPosition());
                    	e.setTexto("clickeado " + e.getId());
                    	notifyItemChanged(getPosition());
                    }
                }
            });

            item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (isSeleccionando())
                        return true;

                    int pos = getPosition();
                    int id = (int) Adaptador.this.getItemId(pos);

                    setSeleccionado(id, true);
                    return true;
                }
            });
        }
    }
}

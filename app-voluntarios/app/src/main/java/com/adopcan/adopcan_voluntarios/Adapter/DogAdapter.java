package com.adopcan.adopcan_voluntarios.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.adopcan.adopcan_voluntarios.DTO.Dog;
import com.adopcan.adopcan_voluntarios.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by sebastian on 02/06/16.
 */
public class DogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public Context mContext;
    private ArrayList<Dog> listItem;
    private View.OnClickListener listener;
    private View itemView;

    public DogAdapter(Context context, ArrayList<Dog> listado) {
        this.mContext = context;
        this.listItem = listado;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dogs_item, viewGroup, false);
        v.setOnClickListener(this);
        return new ViewHolderItem(v, mContext, null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderItem vhHeader = (ViewHolderItem) holder;
        vhHeader.bindPlaceObj(listItem.get(position));
    }


    public Dog getItemByPos(int pos) {
        return listItem.get(pos);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView name, edad;
        Context innerContext;
        View.OnClickListener mListen;

        public ViewHolderItem(final View itemView, Context context, View.OnClickListener listener) {
            super(itemView);
            this.innerContext = context;
            this.mListen = listener;

            foto = itemView.findViewById(R.id.img_thumb);
            name = itemView.findViewById(R.id.itm_name);
            edad = itemView.findViewById(R.id.itm_edad);
        }

        public void bindPlaceObj(Dog item) {
            if (item != null) {
                name.setText(item.getNombre());
                edad.setText(innerContext.getString(R.string.edad_lbl)+item.getEdad());
                //La imagen esta harcodeada xq no hay url de imagenes en el api, pero si las hubiese deberia ser asi
                //Picasso.with(innerContext).load(item.getFoto()).into(foto);
                Picasso.with(innerContext).load("https://static.hogarmania.com/archivos/201505/perro-consejos-416x236x80xX.jpg").into(foto);
            }
        }
    }
}







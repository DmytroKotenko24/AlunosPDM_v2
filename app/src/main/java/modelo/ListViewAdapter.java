package modelo;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import ipleiria.eec.pdm.alunospdm.R;

public class ListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private ArrayList<Pessoa> listaPessoas;
    private Context context;

    public ListViewAdapter(ArrayList<Pessoa> listaPessoas, Context context) {
        this.listaPessoas = listaPessoas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaPessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_layout, parent, false);
        }
        // get current item to be displayed
        Pessoa currentItem = (Pessoa) getItem(position);
        // get the TextView for item number and item name
        TextView textViewItemNumber =
                convertView.findViewById(R.id.textViewNumero);
        TextView textViewItemName =
                convertView.findViewById(R.id.textViewNome);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        //sets the text for item number and item name from the current item object
        textViewItemNumber.setText(Integer.toString(currentItem.getNumero()));
        textViewItemName.setText(currentItem.getNome());
        if (currentItem.getTipo()=='A'){
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.aluno));
        }else
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.professor));
        // returns the view for the current row
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder al = new AlertDialog.Builder(context);
        al.setMessage(listaPessoas.get(position).getNome());
        al.setNeutralButton("Ok", null);
        al.show();
    }
}

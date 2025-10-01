package modelo;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class GestaoPessoas implements Serializable {
    private ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    public void initPessoas() {
        listaPessoas.add(new Pessoa(1111111, "Carlos Paiva", 'A'));
        listaPessoas.add(new Pessoa(2222222, "Rita Santos", 'A'));
        listaPessoas.add(new Pessoa(3333333, "Manuel Saraiva", 'A'));
        listaPessoas.add(new Pessoa(4444444, "Tiago Bento", 'A'));
        listaPessoas.add(new Pessoa(5555555, "Ana Moreira", 'P'));
        listaPessoas.add(new Pessoa(6666666, "Luís Melo", 'A'));
        Collections.sort(listaPessoas);
    }
    public void adicionarPessoa(Pessoa p) {
        listaPessoas.add(p);
        Collections.sort(listaPessoas);
    }
    public ArrayList<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
    public void gravarFicheiro(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("pessoas.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listaPessoas);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Toast.makeText(context, "Could not write people data to internal storage.", Toast.LENGTH_LONG).show();
        }
    }
    public void lerFicheiro(Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput("pessoas.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            listaPessoas = (ArrayList<Pessoa>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Could not read people data from internal storage.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error reading people data from internal storage.", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Error reading people data from internal storage.", Toast.LENGTH_LONG).show();
        }
    }
}

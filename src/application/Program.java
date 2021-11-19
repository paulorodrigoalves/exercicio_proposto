package application;

import entities.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner((System.in));

        List<Produto> produtos = new ArrayList<>();

        System.out.println("Enter file path: ");
        String path = sc.nextLine();

        File arquivo = new File(path);
        String caminho = arquivo.getParent();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            while(line != null){
                String[] textoSeparado = line.split(",");
                String nomeProduto = textoSeparado[0];
                Double valorProduto = Double.parseDouble(textoSeparado[1]);
                int qtdProduto = Integer.parseInt(textoSeparado[2]);

                produtos.add(new Produto(nomeProduto, valorProduto, qtdProduto));
                line = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }

        boolean subpasta = new File(caminho + "/out").mkdir();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "/out/summary.csv"))){
            for (Produto produto: produtos) {
                bw.write(produto.getNome());
                bw.write(",");
                bw.write(String.valueOf(produto.total()));
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } ;
    }
}

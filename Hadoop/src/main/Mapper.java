package br.com.srbit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.util.StringTokenizer;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<Object, Text, Text, IntWritable> {

    private final Text word = new Text();

    @Override
    public void main(String[] args)
         throws IOException, InterruptedException {
        StringTokenizer fb = new StringTokenizer(value.toString());

        while (fb.hasMoreTokens()) {
            String token = fb.nextToken();
            if (token.equals("\"Prompts\":")) {
                String data = fb.nextToken();
                System.out.println(data);
                word.set(data);
                context.write(word);
            }
            if (token.equals("\"Tipo\":")) {
                String tipo = fb.nextToken();
                System.out.println(tipo);
                word.set(tipo);
                context.write(word);
            }
        }
    }
}

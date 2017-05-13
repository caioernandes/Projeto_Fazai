package fazai.com.br.fazai.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Set;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.model.Carrinho;
import fazai.com.br.fazai.model.ItemCardapio;

public class ItensCarrinhoActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_carrinho);

        Carrinho carrinho = CarrinhoComprasActivity.mCarrinho;

        LinearLayout cartLayout = (LinearLayout) findViewById(R.id.cart);

        Set<ItemCardapio> products = carrinho.getProducts();

        Iterator iterator = products.iterator();
        while(iterator.hasNext())
        {
            ItemCardapio itemCardapio = (ItemCardapio) iterator.next();

            // logic
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            TextView name = new TextView(this);
            TextView quantity = new TextView(this);

            name.setText(itemCardapio.nome);
            quantity.setText(Integer.toString(carrinho.getQuantity(itemCardapio)));

            linearLayout.addView(name);
            linearLayout.addView(quantity);

            // display
            name.setTextSize(40);
            quantity.setTextSize(40);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    200, Gravity.CENTER);
            layoutParams.setMargins(20, 50, 20, 50);
            linearLayout.setLayoutParams(layoutParams);

            name.setLayoutParams(new TableLayout.LayoutParams(0,
                    ActionBar.LayoutParams.WRAP_CONTENT, 1));

            quantity.setLayoutParams(new TableLayout.LayoutParams(0,
                    ActionBar.LayoutParams.WRAP_CONTENT, 1));

            name.setGravity(Gravity.CENTER);
            quantity.setGravity(Gravity.CENTER);

            cartLayout.addView(linearLayout);
        }
    }
}

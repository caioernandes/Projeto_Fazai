package fazai.com.br.fazai.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.EstabelecimentoByIdTask;
import fazai.com.br.fazai.http.ItemCardapioByIdTask;
import fazai.com.br.fazai.http.ItemCardapioTask;
import fazai.com.br.fazai.model.Constantes;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.model.ItemCardapio;


public class ItemCardapioDetalheFragment extends Fragment implements LoaderManager.LoaderCallbacks<ItemCardapio> {

    CollapsingToolbarLayout appBarLayout;

    @BindView(R.id.txtTituloItemCardapio)
    TextView mTituloItemCardapio;

    @BindView(R.id.txtDescricaoItemCardapio)
    TextView mDescricaoItemCardapio;

    @Nullable
    @BindView(R.id.image_foto_item_cardapio)
    ImageView mImageItemCardapio;

    @BindView(R.id.fab_item_cardapio)
    FloatingActionButton mFab;

    @BindView(R.id.txtComentarioItemCardapio)
    EditText mComentarioItemCardapio;

    private LoaderManager mLoaderManager;
    private ItemCardapio mItemCardapio;
    private Unbinder unbinder;


    public ItemCardapioDetalheFragment() {
        // Required empty public constructor
    }

    public static ItemCardapioDetalheFragment newInstance(String cardapioId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.CARDAPIO_ID, cardapioId);
        ItemCardapioDetalheFragment fragment = new ItemCardapioDetalheFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_cardapio_detalhe, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        appBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Abrir tela de compra", Toast.LENGTH_SHORT).show();
            }
        });

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(1, getArguments(), this);

        if (!verificaConexao()) {
            Toast.makeText(getActivity(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    @Override
    public Loader<ItemCardapio> onCreateLoader(int id, Bundle args) {
        int cardapioId = args.getInt(Constantes.CARDAPIO_ID);
        return new ItemCardapioByIdTask(getActivity(), /*cardapioId*/ 1);
    }

    @Override
    public void onLoadFinished(Loader<ItemCardapio> loader, ItemCardapio data) {
        if (data != null) {
            mItemCardapio = data;
            updateUI(mItemCardapio);
        } else {
            Toast.makeText(getActivity(), "Erro ao carregar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUI(ItemCardapio data) {
        appBarLayout.setTitle(data.nome);
        mTituloItemCardapio.setText(data.nome);
        mDescricaoItemCardapio.setText(data.descricao);

        if (mImageItemCardapio != null)
            Glide.with(getActivity()).load(data.imagem).into(mImageItemCardapio);
    }

    @Override
    public void onLoaderReset(Loader<ItemCardapio> loader) {

    }
}
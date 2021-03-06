package work.beltran.discogsbrowser.ui.main.collection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyeem.recyclerviewtools.adapter.WrapAdapter;

import rx.Observer;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.main.common.CustomToolbar;
import work.beltran.discogsbrowser.ui.main.common.CircleTransform;

public class CollectionFragment extends work.beltran.discogsbrowser.ui.main.common.RecordsFragment<CollectionRecordsAdapter> {

    public CollectionFragment() {
        // Required empty public constructor
    }

    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }

    @Override
    protected void injectDependencies() {
        ((App) getActivity().getApplication()).getApiComponent().inject(this);
        ((App) getActivity().getApplication()).getApiComponent().inject(adapter);
    }

    @Override
    protected void initHeaderFooter(LayoutInflater inflater, RecyclerView recyclerView, WrapAdapter wrapAdapter) {
        final View header = inflater.inflate(R.layout.header, recyclerView, false);
        wrapAdapter.addHeader(header);
        collection.getUserProfile().subscribe(new Observer<UserProfile>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserProfile userProfile) {
                ((TextView)header.findViewById(R.id.textWantlist)).setText(userProfile.getUsername());
                ImageView imageView = (ImageView) header.findViewById(R.id.imageAvatar);
                picasso.load(userProfile.getAvatar_url())
                        .placeholder(R.drawable.ic_account_circle_black_48px)
                        .fit()
                        .centerCrop()
                        .transform(new CircleTransform())
                        .into(imageView);
                TextView inCollection = (TextView) header.findViewById(R.id.textInCollection);
                inCollection.setText(getResources().getString(R.string.in_collection, userProfile.getNum_collection()));

            }
        });
        CustomToolbar.setToolbar(this, header);
        View footer = inflater.inflate(R.layout.footer, recyclerView, false);
        wrapAdapter.addFooter(footer);
    }

}

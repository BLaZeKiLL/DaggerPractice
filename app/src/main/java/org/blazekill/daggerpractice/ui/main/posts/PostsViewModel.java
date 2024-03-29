package org.blazekill.daggerpractice.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.models.Post;
import org.blazekill.daggerpractice.network.main.MainApi;
import org.blazekill.daggerpractice.util.Resource;
import org.blazekill.daggerpractice.util.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<Post>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading(null));

            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostFromUser(Objects.requireNonNull(Objects.requireNonNull(sessionManager.getAuthUser().getValue()).data).getId())
                .onErrorReturn(throwable -> {
                    Log.e(TAG, "observePosts: ", throwable);
                    Post post = new Post();
                    post.setId(-1);
                    ArrayList<Post> posts = new ArrayList<>();
                    posts.add(post);
                    return posts;
                })
                .map((Function<List<Post>, Resource<List<Post>>>) posts -> {
                    if (posts.size() > 0) {
                        if (posts.get(0).getId() == -1) {
                            return Resource.error("Something went wrong", null);
                        }
                    }
                    return Resource.success(posts);
                })
                .subscribeOn(Schedulers.io())
            );

            posts.addSource(source, listResource -> {
                posts.setValue(listResource);
                posts.removeSource(source);
            });
        }

        return posts;
    }

}

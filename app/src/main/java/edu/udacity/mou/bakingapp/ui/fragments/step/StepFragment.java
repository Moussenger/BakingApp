package edu.udacity.mou.bakingapp.ui.fragments.step;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.fragments.BakingAppFragment;
import edu.udacity.mou.bakingapp.utils.StringUtils;

/**
 * Created by mou on 25/05/17.
 */

public class StepFragment extends BakingAppFragment implements StepContract.View {
    private static final String STEP_ARG = "step";
    private static final String POSITION_ARG = "position";
    private static final String NEXT_ARG = "next";
    private static final String PREVIOUS_ARG = "previous";

    @Inject StepContract.Presenter presenter;

    @Nullable @BindView(R.id.fragment_step_description) protected TextView descriptionTextView;
    @BindView(R.id.fragment_step_player) protected SimpleExoPlayerView playerView;
    @Nullable @BindView(R.id.fragment_step_next_button) protected Button nextButton;
    @Nullable @BindView(R.id.fragment_step_previous_button) protected Button previousButton;
    
    private Step step;
    private int position;
    private boolean hasNext;
    private boolean hasPrevious;
    private SimpleExoPlayer exoPlayer;
    private OnChangeStepListener onChangeStepListener;

    public static StepFragment newInstance(Step step, int position, boolean hasNext, boolean hasPrevious) {
        StepFragment fragment = new StepFragment();
        fragment.setArguments(getArgs(step, position, hasNext, hasPrevious));
        return fragment;
    }

    private static Bundle getArgs(Step step, int position, boolean hasNext, boolean hasPrevious) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_ARG, Parcels.wrap(step));
        bundle.putInt(POSITION_ARG, position);
        bundle.putBoolean(NEXT_ARG, hasNext);
        bundle.putBoolean(PREVIOUS_ARG, hasPrevious);
        return bundle;
    }

    @Override
    public BasePresenter configPresenter() {
        presenter.setView(this);
        return presenter;
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_step;
    }

    @Override
    protected void onViewCreated() {
        loadArguments();
        configDescription();
        configButtons();
        configPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Optional
    @OnClick(R.id.fragment_step_next_button)
    public void onNextStep() {
        if(onChangeStepListener != null) {
            onChangeStepListener.onClickNextStep(position);
        }
    }

    @Optional
    @OnClick(R.id.fragment_step_previous_button)
    public void onPreviousStep() {
        if(onChangeStepListener != null) {
            onChangeStepListener.onClickPreviousStep(position);
        }
    }

    public void setOnChangeStepListener(OnChangeStepListener listener) {
        this.onChangeStepListener = listener;
    }

    private void loadArguments() {
        step = Parcels.unwrap(getArguments().getParcelable(STEP_ARG));
        position = getArguments().getInt(POSITION_ARG);
        hasNext = getArguments().getBoolean(NEXT_ARG);
        hasPrevious = getArguments().getBoolean(PREVIOUS_ARG);
    }

    private void configDescription() {
        if(descriptionTextView != null) {
            descriptionTextView.setText(step.getDescription());
        }
    }

    private void configButtons () {
        if(nextButton != null && previousButton != null) {
            nextButton.setEnabled(hasNext);
            previousButton.setEnabled(hasPrevious);
        }
    }

    private void configPlayer() {
        playerView.setVisibility(View.GONE);

        String url = StringUtils.hasContent(step.getVideoURL()) ? step.getVideoURL() : step.getThumbnailURL();

        if(StringUtils.hasContent(url)) {
            playerView.setVisibility(View.VISIBLE);
            initializePlayer(Uri.parse(step.getVideoURL()));
        }
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            playerView.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(getContext(), getString(R.string.user_agent));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        }
    }

    private void releasePlayer() {
        if(exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private void stop() {
        if(exoPlayer != null) {
            exoPlayer.stop();
        }
    }

    public interface OnChangeStepListener {
        void onClickNextStep(int position);
        void onClickPreviousStep(int position);
    }
}

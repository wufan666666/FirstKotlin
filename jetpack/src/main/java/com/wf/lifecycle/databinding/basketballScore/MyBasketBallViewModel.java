package com.wf.lifecycle.databinding.basketballScore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 * @author : wf
 * @date : 2021年11月10日 10:00
 */
public class MyBasketBallViewModel extends ViewModel {
    private MutableLiveData<Integer> TeamAScore;
    private MutableLiveData<Integer> TeamBScore;


    public MutableLiveData<Integer> getTeamAScore() {
        if (TeamAScore == null) {
            TeamAScore = new MutableLiveData<>();
            TeamAScore.setValue(0);
        }
        return TeamAScore;
    }

    public MutableLiveData<Integer> getTeamBScore() {
        if (TeamBScore == null) {
            TeamBScore = new MutableLiveData<>();
            TeamBScore.setValue(0);
        }
        return TeamBScore;
    }

    public void setTeamAScore(int score) {

        TeamAScore.setValue(TeamAScore.getValue() + score);
    }

    public void setTeamBScore(int score) {

        TeamBScore.setValue(TeamBScore.getValue() + score);
    }



    public void setClearScore() {
        TeamAScore.setValue(0);
        TeamBScore.setValue(0);
    }
}
package com.example.quiz_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDetailsID  implements Serializable {
    private  String contestantId;
    private  String quizId;

    @Override
    public boolean equals(Object object){
        if(this==object) return  true;
        if(!(object instanceof ScoreDetailsID)) return false;
        ScoreDetailsID scoreDetailsID=(ScoreDetailsID) object;
        return scoreDetailsID.contestantId.equals(this.contestantId)&& scoreDetailsID.quizId.equals(this.quizId);
    }


}

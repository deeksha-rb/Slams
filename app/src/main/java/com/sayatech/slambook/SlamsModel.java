package com.sayatech.slambook;

public class SlamsModel {
    String goodName,knownAs,bornOn,zodiacSign,mailID,phoneNumber,relationshipStatus,
            words,favColor,favFood,favPlace,favSport,favMovieStar,favCartoonChar,
            favBandOrSinger,favMovie,roleModel,liveQuote,hobby,crazyAbout,fascinatedBy,
            ambition,genie,rulePresident,badAt,
            describeLove,describeFriendship,aboutYou;
    boolean filled;
    int id;

    public SlamsModel(int id, boolean filled, String goodName,String knownAs,String bornOn, String zodiacSign,
                      String mailID,String phoneNumber,String relationshipStatus,
                      String words,String favColor, String favFood,String favPlace,
                      String favSport,String favMovieStar,String favCartoonChar,
                      String favBandOrSinger,String favMovie,String roleModel,String liveQuote,
                      String hobby,String crazyAbout,String fascinatedBy,
                      String ambition,String genie,String rulePresident,String badAt,
                      String describeLove,String describeFriendship,String aboutYou) {

        this.id = id;
        this.filled = filled;
        this.goodName = goodName;
        this.knownAs = knownAs;
        this.bornOn = bornOn;
        this.zodiacSign = zodiacSign;
        this.mailID = mailID;
        this.phoneNumber = phoneNumber;
        this.relationshipStatus = relationshipStatus;
        this.words = words;
        this.favColor = favColor;
        this.favFood = favFood;
        this.favPlace = favPlace;
        this.favSport = favSport;
        this.favMovieStar = favMovieStar;
        this.favCartoonChar = favCartoonChar;
        this.favBandOrSinger = favBandOrSinger;
        this.favMovie = favMovie;
        this.roleModel = roleModel;
        this.liveQuote = liveQuote;
        this.hobby = hobby;
        this.crazyAbout = crazyAbout;
        this.fascinatedBy = fascinatedBy;
        this.ambition = ambition;
        this.genie = genie;
        this.rulePresident = rulePresident;
        this.badAt = badAt;
        this.describeLove = describeLove;
        this.describeFriendship = describeFriendship;
        this.aboutYou = aboutYou;

    }

    public SlamsModel() {

    }

    public String getGoodName() {
        return goodName;
    }

    public String getKnownAs() {return knownAs;}

    public String getBornOn() {
        return bornOn;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public String getMailID() {
        return mailID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public String getWords() {
        return words;
    }

    public String getFavColor() {
        return favColor;
    }

    public String getFavFood() {
        return favFood;
    }

    public String getFavPlace() {
        return favPlace;
    }

    public String getFavSport() {return favSport;}

    public String getFavMovieStar() {return favMovieStar;}

    public String getFavCartoonChar() {return favCartoonChar;}

    public String getFavBandOrSinger() {return favBandOrSinger;}

    public String getFavMovie() {return favMovie;}

    public String getRoleModel() {return roleModel;}

    public String getLiveQuote() {return liveQuote;}

    public String getHobby() {return hobby;}

    public String getCrazyAbout() {
        return crazyAbout;
    }

    public String getFascinatedBy() {
        return fascinatedBy;
    }

    public String getAmbition() {
        return ambition;
    }

    public String getGenie() {return genie;}

    public String getRulePresident() {return rulePresident;}

    public String getBadAt() {return badAt;}

    public String getDescribeLove() {
        return describeLove;
    }

    public String getDescribeFriendship() {
        return describeFriendship;
    }

    public String getAboutYou() {return aboutYou;}

}


package com.example.rummenigged.archandroidguidelinetest.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rummenigged on 23/03/18.
 */

@Entity(tableName = "cryptocurrency")
public class CryptocurrencyRaw {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_local_db")
    private int inLocalDb;

    @SerializedName("id")
    @ColumnInfo(name = "cryptocurrency_id")
    private String id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("symbol")
    @ColumnInfo(name = "symbol")
    private String symbol;

    @SerializedName("rank")
    @ColumnInfo(name = "rank")
    private int rank;

    @SerializedName("price_usd")
    @ColumnInfo(name = "price_usd")
    private double priceUsd;

    @SerializedName("price_btc")
    @ColumnInfo(name = "price_btc")
    private double priceBtc;

    @SerializedName("24h_volume_usd")
    @ColumnInfo(name = "24h_volume_usd")
    private double volumeUsd24h;

    @SerializedName("market_cap_usd")
    @ColumnInfo(name = "market_cap_usd")
    private double marketCapUsd;

    @SerializedName("available_supply")
    @ColumnInfo(name = "available_supply")
    private double availableSupply;

    @SerializedName("total_supply")
    @ColumnInfo(name = "total_supply")
    private double totalSupply;

    @SerializedName("max_supply")
    @ColumnInfo(name = "max_supply")
    private double maxSupply;

    @SerializedName("percent_change_1h")
    @ColumnInfo(name = "percent_change_1h")
    private double percentChange1h;

    @SerializedName("percent_change_24h")
    @ColumnInfo(name = "percent_change_24h")
    private double percentChange24h;

    @SerializedName("percent_change_7d")
    @ColumnInfo(name = "percent_change_7d")
    private double percentChange7d;

    @SerializedName("last_updated")
    @ColumnInfo(name = "last_updated")
    private double lastUpdated;

    public CryptocurrencyRaw(String id, String name, String symbol, int rank, double priceUsd, double priceBtc, long volumeUsd24h, long marketCapUsd, long availableSupply, long totalSupply, long maxSupply, double percentChange1h, double percentChange24h, double percentChange7d, long lastUpdated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this.volumeUsd24h = volumeUsd24h;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
    }

    public CryptocurrencyRaw() {
    }

    public int getInLocalDb() {
        return inLocalDb;
    }

    public void setInLocalDb(int inLocalDb) {
        this.inLocalDb = inLocalDb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public double getVolumeUsd24h() {
        return volumeUsd24h;
    }

    public void setVolumeUsd24h(double volumeUsd24h) {
        this.volumeUsd24h = volumeUsd24h;
    }

    public double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public double getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(double lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

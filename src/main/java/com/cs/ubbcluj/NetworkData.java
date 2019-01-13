package com.cs.ubbcluj;

public class NetworkData {

    private final String SSID;
    private final String networkType;
    private final String authentication;
    private final String encryption;
    private final String BSSID1;
    private final String signal;
    private final String radioType;
    private final String channel;
    private final String basicRates;
    private final String otherRates;

    private NetworkData(String SSID,
                        String networkType,
                        String authentication,
                        String encryption,
                        String BSSID1,
                        String signal,
                        String radioType,
                        String channel,
                        String basicRates,
                        String otherRates) {
        this.SSID = SSID;
        this.networkType = networkType;
        this.authentication = authentication;
        this.encryption = encryption;
        this.BSSID1 = BSSID1;
        this.signal = signal;
        this.radioType = radioType;
        this.channel = channel;
        this.basicRates = basicRates;
        this.otherRates = otherRates;
    }

    public String getData() {
        return "SSID: '" + SSID + '\'' +
                ", networkType: '" + networkType + '\'' +
                ", authentication: '" + authentication + '\'' +
                ", encryption: '" + encryption + '\'' +
                ", MAC: '" + BSSID1 + '\'' +
                ", signal: '" + signal + '\'' +
                ", radioType: '" + radioType + '\'' +
                ", channel: '" + channel + '\'' +
                ", basicRates: '" + basicRates + '\'' +
                ", otherRates: '" + otherRates + '\'';
    }

    public static class Builder {

        private String SSID;
        private String networkType;
        private String authentication;
        private String encryption;
        private String BSSID1;
        private String signal;
        private String radioType;
        private String channel;
        private String basicRates;
        private String otherRates;

        public NetworkData build() {
            return new NetworkData(SSID, networkType, authentication, encryption, BSSID1,
                    signal, radioType, channel, basicRates, otherRates);
        }

        public Builder withSSID(String SSID) {
            this.SSID = SSID;
            return this;
        }

        public Builder withNetworkType(String networkType) {
            this.networkType = networkType;
            return this;
        }

        public Builder withAuthentication(String authentication) {
            this.authentication = authentication;
            return this;
        }

        public Builder withEncryption(String encryption) {
            this.encryption = encryption;
            return this;
        }

        public Builder withBSSID1(String BSSID1) {
            this.BSSID1 = BSSID1;
            return this;
        }

        public Builder withSignal(String signal) {
            this.signal = signal;
            return this;
        }

        public Builder withRadioType(String radioType) {
            this.radioType = radioType;
            return this;
        }

        public Builder withChannel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder withBasicRates(String basicRates) {
            this.basicRates = basicRates;
            return this;
        }

        public Builder withOtherRates(String otherRates) {
            this.otherRates = otherRates;
            return this;
        }
    }
}

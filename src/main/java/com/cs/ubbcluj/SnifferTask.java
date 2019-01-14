package com.cs.ubbcluj;

import javafx.concurrent.Task;

public class SnifferTask extends Task<String> {

        @Override
        protected String call() throws Exception {
            return SniffingPackets.sniff();
        }

}

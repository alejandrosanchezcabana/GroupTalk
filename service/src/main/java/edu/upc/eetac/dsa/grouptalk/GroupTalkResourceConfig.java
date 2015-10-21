package edu.upc.eetac.dsa.grouptalk;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Alex on 21/10/15.
 */
public class GroupTalkResourceConfig extends ResourceConfig {
    public GroupTalkResourceConfig() {
        packages("edu.upc.eetac.dsa.grouptalk");
    }
}

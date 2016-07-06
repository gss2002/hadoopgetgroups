package org.apache.hadoop;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.Groups;
import org.apache.hadoop.security.UserGroupInformation;

public class GetGroups {

	private static Configuration conf;
	private static UserGroupInformation ugi;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setConfig();
		String usershort;
        UserGroupInformation.setConfiguration(conf);
		System.out.println("Security Enabled: "+UserGroupInformation.isSecurityEnabled());
		 if (UserGroupInformation.isSecurityEnabled()) {
			 try {
				ugi = UserGroupInformation.getCurrentUser();
				System.out.println("UserShortName: "+ugi.getShortUserName());
				System.out.println("UPN: "+ugi.getUserName());
				usershort = ugi.getShortUserName();
				System.out.println("UGI Groups:");
				String[] grouplist = ugi.getGroupNames();
				for( int i = 0; i <= grouplist.length - 1; i++) {
					System.out.println("UGI Group: "+grouplist[i]);
				}
				List<String> groups = Groups.getUserToGroupsMappingService(conf).getGroups(usershort);
				System.out.println("GroupsClass Groups:");
				for (int i = 0; i < groups.size(); i++) {
					System.out.println("GroupsClass Group: "+groups.get(i));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 }

	}

    public static void setConfig(){
    	conf = new Configuration();
    	conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
    	conf.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));
    	conf.addResource(new Path("/etc/hadoop/conf/mapred-site.xml"));
    }
}

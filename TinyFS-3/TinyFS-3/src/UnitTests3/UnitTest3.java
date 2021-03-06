package UnitTests3;

import com.client.ClientFS;
import com.client.ClientFS.FSReturnVals;
import com.master.Master;

/**
 * UnitTest3 for Part 3 of TinyFS
 * @author Shahram Ghandeharizadeh and Jason Gui
 *
 */
public class UnitTest3 {
	
	public static int N = 755;
	static final String TestName = "Unit Test 3: ";

	public static void main(String[] args) {
		Master master = new Master();
		ClientFS cfs = new ClientFS(master);
		UnitTest2 ut2 = new UnitTest2();
		ut2.test2(cfs);

		System.out.println(TestName + "CreateDir /ShahramGhandeharizadeh/CSCI485");
		//String dir1 = "ShahramGhandeharizadeh";
		String dir1 = "Shahram";
		FSReturnVals fsrv = cfs.CreateDir("/" + dir1 + "/", "CSCI485");
		if( fsrv != FSReturnVals.Success ){
			System.out.println("Unit test 3 result: fail!");
    		return;
		}
		
		System.out.println(TestName + "CreateFile Lecture1/2/.../15 in /ShahramGhandeharizadeh/CSCI485");
		for(int i = 1; i <= N; i++){
			fsrv = cfs.CreateFile("/" + dir1 + "/CSCI485/", "Lecture" + i);
			if( fsrv != FSReturnVals.Success ){
				System.out.println("Unit test 3 result: fail!");
	    		return;
			}
		}
		
		System.out.println(TestName + "DeleteFile Lecture1/2/.../15 in /ShahramGhandeharizadeh/CSCI485");
		for(int i = 1; i <= N; i++){
			fsrv = cfs.DeleteFile("/" + dir1 + "/CSCI485/", "Lecture" + i);
			if( fsrv != FSReturnVals.Success ){
				System.out.println("Unit test 3 result: fail!");
	    		return;
			}
		}
		
		System.out.println(TestName + "CreateFile /Shahram/2/Lecture1, /Shahram/2/Lecture2, ...., /Shahram/2/Lecture15");
		String dir2 = "Shahram";
		for(int i = 1; i <= N; i++){
			fsrv = cfs.CreateFile("/" + dir2 + "/2i/", "Lecture" + i);
			if( fsrv != FSReturnVals.SrcDirNotExistent ){
				System.out.println("Unit test 3 result: fail!");
	    		return;
			}
		}
		
		System.out.println(TestName + "DeleteFile /Shahram/2/Lecture1, /Shahram/2/Lecture2, ...., /Shahram/2/Lecture15");
		for(int i = 1; i <= N; i++){
			fsrv = cfs.DeleteFile("/" + dir2 + "/2i/", "Lecture" + i);
			if( fsrv != FSReturnVals.SrcDirNotExistent ){
				System.out.println("Unit test 3 result: fail!");
	    		return;
			}
		}
		
		System.out.println(TestName + "Success!");
	}
}

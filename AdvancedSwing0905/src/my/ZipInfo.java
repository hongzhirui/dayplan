package my;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipInfo
{
	public int fileCount; // zip文件里的文件个数
	public long totalSize; // zip文件里所有文件的总大小

	List<ZipEntry> entries = new ArrayList<>();

	// 显示的时候将文件夹排在前面，文件排在后面
	public void sort()
	{
		
	}

	// 工具方法: 从ZipFile中获取目录信息
	public static ZipInfo from(ZipFile zipFile) throws Exception
	{
		ZipInfo info = new ZipInfo();
		
		Enumeration<?> entries = zipFile.entries();
		while (entries.hasMoreElements())
		{
			ZipEntry entry = (ZipEntry) entries.nextElement();
			info.entries.add(entry);
			
			if ( !entry.isDirectory())
			{
				info.fileCount += 1; // 文件个数
				info.totalSize += entry.getSize();
			}
		}
		
		return info;
	}
}

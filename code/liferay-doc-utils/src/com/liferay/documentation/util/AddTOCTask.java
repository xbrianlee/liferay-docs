package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class AddTOCTask extends Task {

	@Override
	public void execute() throws BuildException {

		String docDir = _docdir;
		String productType = _productType;

		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
		}

		for (String dirType : dirTypes) {

			List<String> fileList = DocsUtil.getMarkdownFileList(docDir, dirType);
			List<String> fileListNoTOC = new ArrayList<String>();

			try {
				fileListNoTOC = getFilesWithNoTOC(fileList);
				addTOCs(fileListNoTOC);
			} catch (IOException e) {
				throw new BuildException(e.getLocalizedMessage());
			}

		}
	}

	private static void addTOCs(List<String> fileListNoTOC) throws IOException {

		for (int i = 0; i < fileListNoTOC.size(); i++) {
			String filenameNoTOC = fileListNoTOC.get(i);
			File inFile = new File(filenameNoTOC);
			File outFile = new File(filenameNoTOC);
			String outFileTmp = outFile + ".tmp";

			System.out.println("Adding TOC syntax for " + filenameNoTOC);

			LineNumberReader in =
					new LineNumberReader(new FileReader(inFile));

			BufferedWriter out =
					new BufferedWriter(new FileWriter(outFileTmp));

			String line;
			boolean tocAdded = false;
			while ((line = in.readLine()) != null) {

				if (line.startsWith("#") && !line.startsWith("##") && !tocAdded) {
					out.append(line);
					out.append("\n\n");
					out.append(tocSyntax);
					out.append("\n");

					tocAdded = true;
				}
				else {
					out.append(line);
					out.append("\n");
				}

			}

			in.close();

			out.flush();
			out.close();

			// Replace original file with modified temp file

			FileUtils.copyFile(
					new File(outFileTmp),
					new File(filenameNoTOC));

			FileUtils.forceDelete(new File(outFileTmp));
		}
	}

	private static List<String> getFilesWithNoTOC(List<String> fileList)
			throws IOException {

		List<String> fileListNoTOC = new ArrayList<String>();

		for (int i = 0; i < fileList.size(); i++) {
			String filename = fileList.get(i);
			File inFile = new File(filename);

			LineNumberReader in =
					new LineNumberReader(new FileReader(inFile));

			String line;
			boolean tocExists = false;
			//Integer tocLineNum = null;
			int tocLineNum = -2;

			while ((line = in.readLine()) != null) {

				if (line.startsWith(tocSyntax)) {

					tocExists = true;
					tocLineNum = in.getLineNumber();
				}
				if (in.getLineNumber() == (tocLineNum + 1) && tocExists) {
					if (!line.equals("")) {
						in.close();
						throw new BuildException("Filename: " + filename + ":" +
								in.getLineNumber() +  "The line following the TOC syntax should " +
								"be blank.");
					}
				}
			}

			if (!tocExists) {
				fileListNoTOC.add(filename);
			}

			in.close();
		}

		return fileListNoTOC;
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	private static String tocSyntax = "[TOC levels=1-4]";

	private String _docdir;
	private String _productType;
}

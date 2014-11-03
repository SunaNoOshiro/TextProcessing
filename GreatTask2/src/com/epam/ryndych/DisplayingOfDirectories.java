package com.epam.ryndych;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class DisplayingOfDirectories {
	private MenuScanner in = MenuScanner.getInstance();
	private Path previosPath = Paths.get("C:\\").getRoot();
	private Path currentPath = Paths.get("C:\\").getRoot();

	private void showInfo() {
		System.out
				.println("���� �������� ������� �������� ������� ������,\n"
						+ "�� ����� ������� �����.��� ���� ��� ������� ���� � ����� ����\n"
						+ "������������ ������� ������������ ���������:\n\n"
						+ "\t'cd path' �� 'cd ..'\t��� ���� �� ���������(��������� 'cd D:\\')\n"
						+ "\t'dir path' �� 'dir'\t��� ����������� ����� �����\n"
						+ "\t'choose file.txt'\t��� ������ ����� � ����� ���������� �������\n"
						+ "\t'info' \t\t\t��� ������� ��������\n"
						+ "\t'exit' \t\t\t��� ������ � ��������\n"
						+ "���������:\nchoose C:\\Users\\Petro\\git\\TextProcessing\\GreatTask2\\input.txt ");
		System.out.println("(�������� Enter, ��� ���������� )");
	}

	public void execute() {
		showInfo();
		in.nextLine();
		while (true) {
			System.out.print(currentPath + "$ ");
			parse();

		}
	}

	private void parse() {

		String inputString = in.nextLine();
		String args[] = inputString.split(" ");
		if (args[0].equals("cd")) {
			if (args.length == 2) {
				if (args[1].equals("..")) {
					goToThePreviosDir();
				} else {
					goToTheDir(args[1]);
				}
			} else {
				System.out.println(currentPath
						+ "$ ������������ ������ ������� 'cd'");
			}
		} else if (args[0].equals("dir")) {
			if (args.length == 2)
				showDirInfo(args[1]);
			else if (args.length == 1)
				showDirInfo();
			else {
				System.out.println(currentPath
						+ "$ ������������ ������ ������� 'dir' ");
			}
		} else if (args[0].equals("choose")) {
			if (args.length == 2) {
				chooseFile(args[1]);
			} else {
				System.out.println(currentPath
						+ "$ ������������ ������ ������� 'choose' ");
			}

		} else if (args[0].equals("info")) {
			showInfo();

		} else if (args[0].equals("exit")) {
			System.exit(0);
		} else {
			System.out.println(currentPath + "$ ����������� �������");
		}
	}

	private void goToTheDir(String path) {
		Path ptr = currentPath;
		if (path.startsWith("C:\\") || path.startsWith("D:\\")) {
			try {
				ptr = Paths.get(path);
			} catch (InvalidPathException e) {

				goToThePreviosDir();
				System.out.println(currentPath + "$ ������������ ����");
			}
		} else {

			try {
				ptr = Paths.get(currentPath.toString() + "\\" + path);
			} catch (InvalidPathException e) {

				goToThePreviosDir();
				System.out.println(currentPath + "$ ������������ ����");
			}
		}

		if (!isExist(ptr)) {
			goToThePreviosDir();
			System.out.println(currentPath + "$ ������������ ����");
		} else {
			currentPath = ptr;
		}
	}

	private void goToThePreviosDir() {
		if (currentPath.compareTo(Paths.get("C:\\")) != 0
				&& currentPath.compareTo(Paths.get("D:\\")) != 0) {
			previosPath = currentPath.getParent();
			currentPath = previosPath;
		}
	}

	private boolean isExist(Path path) {
		File f = new File(path.toString());
		return f.exists();
	}

	private boolean isExist(String path) {
		File f = new File(path);
		return f.exists();
	}

	private String getSize(long size) {
		if (size / 1024 > 1024 * 1024)
			return size / 1024 / 1024 / 1024 + " GB";
		else if (size / 1024 > 1024)
			return size / 1024 / 1024 + " MB";
		else if (size / 1024 > 0)
			return size / 1024 + " KB";
		else
			return size + " B";
	}

	private void showDirInfo() {
		try (DirectoryStream<Path> stream = Files
				.newDirectoryStream(currentPath)) {

			System.out.println("Path- " + currentPath);
			System.out
					.println("\t��� �������� ���������� \t|�����\t|�� �����?\t|����� �����"
							+ "\n\t---------------------------------------------------------------------");
			for (Path file : stream) {
				File f = new File(file.toString());
				Date d = new Date(f.lastModified());
				BasicFileAttributes attributes = Files.readAttributes(file,
						BasicFileAttributes.class);
				System.out.println("\t" + d.toString() + "\t"
						+ getSize(attributes.size()) + "\t"
						+ attributes.isDirectory() + "\t\t"
						+ file.getFileName());
			}
		} catch (IOException e) {
			System.out.println(currentPath + "$ ������������ ����");
			goToThePreviosDir();
		}
	}

	private void showDirInfo(String path) {
		if (!isExist(path)) {
			System.out.println(currentPath + "$ ������������ ����");
		} else
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths
					.get(path))) {
				System.out.println("Path- " + Paths.get(path));
				System.out
						.println("\t��� �������� ���������� \t|�����\t|�� �����?\t|����� �����"
								+ "\n\t---------------------------------------------------------------------");
				for (Path file : stream) {
					File f = new File(file.toString());
					Date d = new Date(f.lastModified());
					BasicFileAttributes attributes = Files.readAttributes(file,
							BasicFileAttributes.class);
					System.out.println("\t" + d.toString() + "\t"
							+ getSize(attributes.size()) + "\t"
							+ attributes.isDirectory() + "\t\t"
							+ file.getFileName());
				}
				// System.out.print(currentPath+"<< ");
			} catch (IOException e) {
				System.out.println(currentPath + "$ ������������ ����");
				goToThePreviosDir();
			}
	}

	private void chooseFile(String path) {
		if (!isExist(path)) {
			System.out.println(currentPath + "$ ������������ ����");
		} else {
			new Task().execute(path);
		}

	}
}

package strategy;

public class FileLoader implements FileChooser{

	private FileChooser fileChooser;
	
	
	public FileLoader(FileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	@Override
	public void save(String path) {
		fileChooser.save(path);
	}


	public FileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(FileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

}

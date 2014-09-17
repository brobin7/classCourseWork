#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <sys/types.h>
#include <vector>
#include <sys/stat.h>
#include <unistd.h>
#include <cstring>
#include <sstream>
#include <errno.h>
#include <string>
#include <ftw.h>
#include <stdint.h>
#include <sstream>
#include <dirent.h>

using namespace std;

int size;
int sign;
int files;
long lF;
long sF;
long fS;
string lFile;
string sFile;

string lDir;
string sDir;

int directories;
int unrd;
int cd;
int fileNum;
int dCount;
int vectorSize;
long tFile;
char* ftype;

long dSize;

vector<string>treedir;

string ToString(long thing){
	ostringstream oss;
	oss << thing;
	return oss.str();
}

string ToString(int thing)
{
	ostringstream oss;
	oss << thing;
	return oss.str();
}

static int printTheInformation(const char *fpath, const struct stat *sb, int tflag, struct FTW *ftwbuf)
{
	cout<< fpath <<endl;
	return 0;           
}

static int printSizes(const char *fpath, const struct stat *sb, int tflag, struct FTW *ftwbuf){

	if(sign == 1){
		if(sb->st_size > size){
			cout<< fpath <<endl;
		}

	}

	if(sign == 0){
		if(sb->st_size < size){
			cout<< fpath <<endl;
		}
	}

	if(sign == 2){
		if(sb->st_size == size){
			cout << fpath << endl;
		}
	}
	return 0;
}

void printSummary(){
	cout << "********************************************" << endl;
	cout << "  Total Files: " << files <<  endl;
	cout << "  The total size of the Files: " << fS << endl;
	cout << "  Total Directories: " << directories <<  endl;
	cout << "  Total Amount of Unreadable Directories: " << unrd << endl;
	cout << "  Total Amount of Character Devices: " << cd << endl;
	cout << "********************************************";

}

static int getInfo(const char *fpath, const struct stat *sb, int tflag, struct FTW *ftwbuf){

	if(tflag == 3){
		DIR *dpdf; struct dirent *epdf;
		dpdf = opendir(fpath);
		if (dpdf != NULL){
			while (epdf = readdir(dpdf)){
				if(epdf->d_type == DT_REG){
					fileNum++;
					FILE *fp;

					fp = fopen(fpath, "rb");

					if (NULL == fp) {
						cout << "BAD FILE EXITING...." << endl; 
						exit (1);
					}

					if (fseek(fp, 0 , SEEK_END) != 0) {
						cout << "BAD FILE EXITING...." << endl; 
						exit (1);
					}
					long file_size;

					file_size = ftell(fp);
					fclose(fp);

					tFile = tFile + file_size;
				}
				if(epdf->d_type == DT_DIR){
					dCount++;
				}

			}

		}
		closedir(dpdf);

		dCount = dCount - 2;

		if(dCount < 0){
			dCount = 0;
		}

		string p1, p2, p3, p4, p5, p6, pF, p7, temp;
		p1 = fpath + ftwbuf->base;
		p2 = " ( ";
		p3 = ToString(dCount);
		p4 = " dirs, ";
		p5 = ToString(fileNum);	
		p6 = " files, ";
		pF = ToString(tFile);
		p7 = " bytes)";
		temp = p1 + p2 + p3 + p4 + p5 + p6 + pF + p7;

		treedir.push_back(temp);
		vectorSize++;


		dCount = 0;
		tFile = 0;
		fileNum = 0;
	}

	return 0;

}

static int doLType(const char *fpath, const struct stat *sb, int tflag, struct FTW *ftwbuf){
	if((strcmp(ftype, "f"))==0){
		if (lF < sb->st_size){
			lFile = fpath;
			lF = sb->st_size;
		}

	}

	long dirSize;

	if((strcmp(ftype, "dir"))==0){
		DIR *dpdf; struct dirent *epdf;
		long file_size;
		dirSize = 0;

		dpdf = opendir(fpath);
		if (dpdf != NULL){
			while (epdf = readdir(dpdf)){
				if(epdf->d_type == DT_REG){
					FILE *fp;

					fp = fopen(fpath, "rb");

					if (NULL == fp) {
						cout << "BAD FILE EXITING...." << endl; 
						exit (1);
					}

					if (fseek(fp, 0 , SEEK_END) != 0) {
						cout << "BAD FILE EXITING...." << endl; 
						exit (1);
					}
					file_size = ftell(fp);
					fclose(fp);
					dirSize = dirSize + file_size;
				}

			}

		}
		closedir(dpdf);
		if (dSize < dirSize){
			lDir = fpath;
			dSize = dirSize;
		}

	}
	return 0;
}

static int tabulateSummary(const char *fpath, const struct stat *sb, int tflag, struct FTW *ftwbuf){

	if(tflag == FTW_DNR){
		unrd++;
	}

	if(S_ISCHR(tflag)){
		cd++;
	}


	if(tflag==FTW_F){
		files++;
		fS = fS + sb->st_size;
	}

	if(tflag==FTW_D){
		directories++;
	}

	return 0;
}

static int doSType(const char *fpath, const struct stat *sb, int tflag, struct FTW *ftwbuf){
	if((strcmp(ftype, "f"))==0){
		if (sF > sb->st_size){
			sFile = fpath;
			sF = sb->st_size;
		} else if (sF == sb->st_size){
			sFile = fpath;
			sF = sb->st_size;
		}

	}

	long dirSize;

	if((strcmp(ftype, "dir"))==0){
		DIR *dpdf; struct dirent *epdf;
		long file_size;
		dirSize = 0;

		dpdf = opendir(fpath);
		if (dpdf != NULL){
			while (epdf = readdir(dpdf)){
				if(epdf->d_type == DT_REG){
					FILE *fp;

					fp = fopen(fpath, "rb");

					if (NULL == fp) {
						cout << "BAD FILE EXITING...." << endl; 
						exit (1);
					}

					if (fseek(fp, 0 , SEEK_END) != 0) {
						cout << "BAD FILE EXITING...." << endl; 
						exit (1);
					}
					file_size = ftell(fp);
					fclose(fp);
					dirSize = dirSize + file_size;
				}

			}

		}
		closedir(dpdf);

		if (dSize > dirSize){
			sDir = fpath;
			dSize = dirSize;
		} else if (dSize == dirSize){
			sDir = fpath;
			dSize = dirSize;
		}

		
	}
	return 0;
}


void printFiles(int arg, char * stuff, char * tFile){
	int flags = 0;

	if (arg > 2 && strchr(tFile, 'd') != NULL)
		flags |= FTW_DEPTH;
	if (arg > 2 && strchr(tFile, 'p') != NULL)
		flags |= FTW_PHYS;
	if (nftw((arg < 2) ? "." : stuff, printTheInformation, 20, flags) == -1) {
		perror("nftw");
		exit(EXIT_FAILURE);
	}

}

void summarizeFiles(int arg, char * stuff, char * tFile){
	int flags = 0;

	if (arg > 2 && strchr(tFile, 'd') != NULL)
		flags |= FTW_DEPTH;
	if (arg > 2 && strchr(tFile, 'p') != NULL)
		flags |= FTW_PHYS;
	if (nftw((arg < 2) ? "." : stuff, tabulateSummary, 20, flags) == -1) {
		perror("nftw");
		exit(EXIT_FAILURE);
	}
}

void doFiles(int arg, char * stuff, char * tFile){
	int flags = 0;

	if (arg > 2 && strchr(tFile, 'd') != NULL)
		flags |= FTW_DEPTH;
	if (arg > 2 && strchr(tFile, 'p') != NULL)
		flags |= FTW_PHYS;
	if (nftw((arg < 2) ? "." : stuff, getInfo, 20, flags) == -1) {
		perror("nftw");
		exit(EXIT_FAILURE);
	}
}

void parseSizes(int arg, char * stuff, char * tFile){
	int flags = 0;

	if (arg > 2 && strchr(tFile, 'd') != NULL)
		flags |= FTW_DEPTH;
	if (arg > 2 && strchr(tFile, 'p') != NULL)
		flags |= FTW_PHYS;
	if (nftw((arg < 2) ? "." : stuff, printSizes, 20, flags) == -1) 
		exit(EXIT_FAILURE);	

}
void processSAS(char * arg){
	char theSign;
	char * tArg;

	tArg = arg;
	theSign = tArg[0];

	char * val;
	val = new char[strlen(tArg)];

	if(theSign == '+'){
		sign = 1;
	}

	else if(theSign == '-'){
		sign = 0;
	} else {
		sign = 2;

	}

	for(int i = 0; i < strlen(tArg); i++){
		val[i] = tArg[i+1];
	}

	size = atoi(val);
}

void processLType(int arg, char * stuff, char * tFile){
	int flags = 0;

	if (arg > 2 && strchr(tFile, 'd') != NULL)
		flags |= FTW_DEPTH;
	if (arg > 2 && strchr(tFile, 'p') != NULL)
		flags |= FTW_PHYS;
	if (nftw((arg < 2) ? "." : stuff, doLType, 20, flags) == -1) 
		exit(EXIT_FAILURE);	

}
void processSType(int arg, char * stuff, char * tFile){
	int flags = 0;

	if (arg > 2 && strchr(tFile, 'd') != NULL)
		flags |= FTW_DEPTH;
	if (arg > 2 && strchr(tFile, 'p') != NULL)
		flags |= FTW_PHYS;
	if (nftw((arg < 2) ? "." : stuff, doSType, 20, flags) == -1) 
		exit(EXIT_FAILURE);	

}

void printHelp(){
	cout << "HeLp!" << endl;
	cout << endl;
	cout << endl;
	cout << "-print: prints out all the files and directories starting at the given filepath." << endl;
	cout << endl;
	cout << "-size: prints out all the files and directories starting at the given filepath, if and only if, the size is greater or less than the amount typed into the command prompt. ";
	cout << "This is based on if the user types in a + or a -. For example, if the user types in +100000, print out all the files";
	cout << " greater than 100000. If the user types in -100000, print out all the files less than 100000. If no sign is present";
	cout << " then print out the files that are exactly that numbers size." << endl;
	cout << endl;
	cout << "-summarize: prints out a table of information. Gives you the number of files, the total size of the files, ";
	cout << "the total directories, the total amount of unreadable directories and the total amount of character devices." << endl;
	cout << endl;
	cout << "-treedir: prints out the directory name, the number of directories in the directory, the number of files in the directory, and the total size of the files in the directory." <<endl;	
	cout <<"-ltype: takes the argument f and dir after it and returns the largest file of that type." << endl;
	cout <<"-stype: takes the argument f and dir after it and returns the largest file of that type.";

}

int main(int argc, char *argv[])
{	
	vectorSize = 0; sign = -1; files = 0; fS = 0; unrd = 0; cd = 0; fileNum = 0; dCount = 0; tFile = 0; lF = 0; dSize = 0;
	for(int i = 0; i < argc; i++){

		if((strcmp(argv[i], "-print")) == 0){
			printFiles(argc, argv[1], argv[2]);
		}

		if((strcmp(argv[i],"-size"))==0){
			processSAS(argv[i+1]);
			parseSizes(argc, argv[1], argv[2]);
		}

		if((strcmp(argv[i],"-summarize"))==0){
			summarizeFiles(argc, argv[1], argv[2]);
			printSummary();

		}

		if((strcmp(argv[i],"-treedir"))==0){
			doFiles(argc, argv[1], argv[2]);
			for(int i = vectorSize - 1; i >= 0; i--){
		cout<< treedir.at(i) <<endl;
	}		
		}
		if((strcmp(argv[i], "-help"))==0){
			printHelp();
		}
		if((strcmp(argv[i],"-ltype"))==0){
			ftype = argv[i+1];
			processLType(argc, argv[1], argv[2]);

			if((strcmp(ftype, "f"))==0)
				cout << lFile << endl;

			if((strcmp(ftype,"dir"))==0)
				cout << lDir << endl;

		}
		if((strcmp(argv[i],"-stype"))==0){
			ftype = argv[i+1];
			processSType(argc, argv[1], argv[2]);

			if((strcmp(ftype,"f"))==0)
				cout << sFile << endl;

			if((strcmp(ftype,"dir"))==0)
				cout << sDir << endl;
		}



	}	
}


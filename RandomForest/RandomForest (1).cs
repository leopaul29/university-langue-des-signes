using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Linq;
using Emgu.CV;
using Emgu.CV.Structure;
using Emgu.CV.ML;
using Emgu.CV.ML.MlEnum;
using Emgu.CV.ML.Structure;
using Emgu.CV.UI;
using Emgu.Util;
using System.Xml.Serialization;
using System.Xml.Schema;
using System.Globalization;
using System.Drawing;
using System.Runtime.InteropServices;

namespace KinectHeadTracker
{
    public static class RandomForest
    {
        /*
         * 
         */ 
        public static void record_donnees(string filename, double[] donnees)
        {
            using (FileStream fs = new FileStream(filename, FileMode.Append))
            {

                using (StreamWriter writer = new StreamWriter(fs, Encoding.Default))
                {
                    string ligne = "";
                    for (int i = 0; i < donnees.Length; i++)
                    {
                        ligne += donnees[i] + ";";

                    }

                    writer.WriteLine(ligne);
                }
            }
        }

        /*
         * 
         */ 
        public static void csvToTableau(out Matrix<double> tableau, out Matrix<double> response, string file)
        {

            string[] rows = File.ReadAllLines(file);
            int varCount = rows[0].Split(';').Length;
            tableau = new Matrix<double>(rows.Length - 1, varCount - 1);
            response = new Matrix<double>(rows.Length - 1, 1);

            for (int j = 1; j < rows.Length; j++)
            {
                string[] valeurs = rows[j].Split(';');
                for (int i = 0; i < valeurs.Length - 1; i++)
                {
                    double res;
                    
                    Double.TryParse(valeurs[varCount - 1], out res);
                    
                    response[j - 1, 0] = res; 
                    string v = valeurs[i];
                    v = v.Replace(',', '.');
                    double d;
                    
                    Double.TryParse(v, out d);
                    tableau[j - 1, i] = d;
                }
            }
        }

        /*
         * 
         */ 
        public static void stringToTableau_1(string str, out double []str_t, out double str_r){
            string[] valeurs = str.Split(';');
            int varCount = valeurs.Length;
            str_t = new double[varCount];
            str_r = 0;
            for (int i = 0; i < valeurs.Length; i++)
            {
                Double.TryParse(valeurs[varCount - 1], out str_r);
                string v = valeurs[i];
                double d;
                Double.TryParse(v, out d);
                str_t[i] = d;
            }
        }

        /*
         * 
         */ 
        public static void stringToTableau_2(string str, out double[] str_t)
        {
            string[] valeurs = str.Split(';');
            int varCount = valeurs.Length;
            str_t = new double[varCount-1];
            for (int i = 0; i < varCount - 1; i++)
            {
                string v = valeurs[i];
                double d;
                Double.TryParse(v, out d);
                str_t[i] = d;
            }
        }

        
        
        /*
         * Convertit les lignes du fichier file en ligne d'une matrice
         * */
        public static void csvToTableau_2(out Matrix<double> tableau, out Matrix<double> response, string file)
        {

            string[] rows = File.ReadAllLines(file);
            int varCount = rows[0].Split(';').Length;
            tableau = new Matrix<double>(rows.Length - 1, varCount);
            response = new Matrix<double>(rows.Length - 1, 1);

            for (int j = 1; j < rows.Length; j++)
            {
                string[] valeurs = rows[j].Split(';');
                for (int i = 0; i < valeurs.Length; i++)
                {
                    double res = 0;
                    Double.TryParse(valeurs[varCount - 1], out res);

                    response[j - 1, 0] = res; 
                    string v = valeurs[i];
                    double d;
                    Double.TryParse(v, out d);
                    tableau[j - 1, i] = d;
                }
            }
        }

        
        
        /*
         * Convertit du type Matrix<double> au type tableau [,]
         * */
        public static void matrixToTableau(Matrix<double> m, out double[,] t)
        {

            t = new double[m.Rows, m.Cols];

            for (int i = 0; i < m.Rows; i++)
            {
                for (int j = 0; j < m.Cols; j++)
                {
                    t[i, j] = m[i, j];
                }
            }
        }


        /*
         * Cette fonction récupère la ligne @i du tableau @m et le copie dans le tableau @ligne
         * */
        public static void getRow(double[,] m, out double[] ligne, int i)
        {
            ligne = new double[m.GetUpperBound(1) + 1];

            for (int j = 0; j <= m.GetUpperBound(1); j++)
            {
                ligne[j] = m[i, j];
            }
        }

        /*
         * Cette fonction convertit le fichier @filename en Matrix<float> 
         * sans prendre en compte les lignes du fichier dont la dernière colonne est nulle 
         * */
        public static void csvToFloat_(out Matrix<float> d, out Matrix<float> r, string filename)
        {
            Matrix<double> data, response;

            csvToTableau(out data, out response, filename);

            List<int> liste = new List<int>();
            for (int i = 0; i < response.Rows; i++)
            {
                for (int j = 0; j < response.Cols; j++)
                {
                    if (response[i, j] != 0)
                    {
                        liste.Add(i);
                    }
                }
            }


            d = new Matrix<float>(liste.Count, data.Cols);
            r = new Matrix<float>(liste.Count, response.Cols);

            //Console.WriteLine(d.Rows);
            //Console.WriteLine(d.Cols);

            for (int i = 0; i < liste.Count; i++)
            {

                r[i, 0] = (float)response[liste.ElementAt<int>(i), 0];
                for (int j = 0; j < data.Cols; j++)
                {
                    d[i, j] = (float)data[liste.ElementAt<int>(i), j];
                }

            }

        }

        /*
         * 
         */ 
        public static void csvToFloat_2(Matrix<double> d, out Matrix<float> mat)
        {

            mat = new Matrix<float>(d.Cols, d.Rows);
            
            //Console.WriteLine(mat.Rows);
            //Console.WriteLine(mat.Cols);
            for (int i = 0; i < mat.Rows; i++)
            {
                for (int j = 0; j < mat.Cols; j++)
                {
                    mat[i,j] = (float)d[j,i];
                }

            }

        }

        /*
         * 
         */ 
        public static void csvToFloat(out Matrix<float> d, out Matrix<float> r, string filename)
        {
            Matrix<double> data, response;

            csvToTableau(out data, out response, filename);

            d = new Matrix<float>(data.Rows, data.Cols);
            r = new Matrix<float>(response.Rows, response.Cols);
            //Console.WriteLine(d.Rows);
            //Console.WriteLine(d.Cols);
            for (int i = 0; i < data.Rows; i++)
            {

                r[i, 0] = (float)response[i, 0];
                for (int j = 0; j < data.Cols; j++)
                {
                    d[i, j] = (float)data[i, j];
                    
                }

            }

        }

        /*
         * Cette fonction calcule le nombre d'éléments de la classe v dans la base d'apprentissage
         * */
        public static double moyenne(Matrix<float> r, float v)
        {
            double moy = 0;
            for (int i = 0; i < r.Rows; i++)
            {
                for (int j = 0; j < r.Cols; j++)
                {
                    if (r[i, j] == v)
                    {
                        moy++;
                    }
                }
            }

            return moy;

        }   
    }
}

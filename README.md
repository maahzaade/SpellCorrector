# SpellCorrector

COMP 6651: Algorithm Design Techniques
Fall 2017: Programming Assignment 1
Due: October 8, 2017 at midnight
1 Problem
Your task is to write and test two simple spelling corrector algorithms.
You are given as input three les. The rst le is called vocab.txt, and is a dictionary
of valid words, with each word on a separate line. The number of words is at most 400; 000
and each word is at most 30 characters long. The second le is called sentence.txt, and
contains a sentence of at most 20 words, each word being at most 30 characters long, and
being separated by one or more empty spaces. The third le is called MaxDistance.txt and
contains a single integer value k that lies between 0 and 5.
Your job is to break up the sentence into words, and for each word w in the sentence, that
is not in the dictionary, 
ag it, and give a list of possible spelling corrections, that is, words
in the dictionary that are at most Levenshtein distance k from the misspelled word. The
Levenshtein distance between a word s and a word t is the number of additions, deletions,
and substitutions of characters required to transform s to t.
Write your output into a le called MisspelledWords.txt. On each line, write a mis-
spelled word found in the sentence, followed by a colon, and then the list of possible cor-
rections, separated by a comma and then a space. The last correction will be followed by
nothing. Each misspelled word will be on a dierent line. If there are no misspelled words
in the sentence, the output le should simply contain the number 0.
To nd the list of spelling corrections, you should implement two algorithms:
1. A simple linear search for the current word w in the dictionary, checking for each word
in the dictionary if it is distance  k from the searched word w.
2. An algorithm using BK-trees.

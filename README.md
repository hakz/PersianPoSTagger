PersianPoSTagger
================

The code for training a Maximum Entropy Persian Part of Speech Tagger based on OpenNLP

The purpose of this project is to train a MaxEnt (Maximum Entropy) Persian Part of Speech Tagger.The project includes three parts:

## 1. Port: 
First we port the [BijanKhan](http://ece.ut.ac.ir/dbrg/bijankhan/) corpus into a format that is acceptable by [OpenNLP](http://opennlp.apache.org/) training module. Since the corpus is too large, you must download the unicode [corpus](http://ece.ut.ac.ir/dbrg/bijankhan/Corpus/BijanKhan_Corpus_Processed.zip) from University of Tehran portal. And then save it under `corpus/BijanKhan_original.txt`. Once you run the Port program, a modified corpus will be created: `corpus/BijanKhan_opennlp.txt`
 
## 2. Train:
Now that you have the proper training corpus, you can train a model by running `Train.scala`. This will create the model under `model/fa-pos-maxent.bin`

## 3. Try it out:
`Sample.scala` is a small sample program that shows how to run the PoS Tagger on piece of a news text.

# Modified Tag Set
The [defined tag set](http://ece.ut.ac.ir/dbrg/bijankhan/Corpus/Bijankhan-tagset-description.txt) is modified so that OpenNLP pick up the complete tag names. We have removed the _s. So the new tag set is as follows:

        Tag		Description
	----------------------------------
	ADJ		Adjective, General
	ADJCMPR	        Adjective, Comparative
	ADJINO		Past Participle
	ADJORD		Adjective, Ordinal
	ADJSIM		Adjective, Simple
	ADJSUP		Adjective, Superlative
	ADV		Adverb, General
	ADVEXM		Adverb, Exemplar
	ADVI		Adverb, Question
	ADVNEGG	        Adverb, Negation
	ADVNI		Adverb, Not Question
	ADVTIME	        Adverb, Time
	AR		Arabic Word
	CON		Conjunction
	DEFAULT		Default
	DELM		Delimiter
	DET		Determiner
	IF		Conditional
	INT		Interjection
	MORP		Morpheme
	MQUA		Modifier of Quantifier
	MS		Mathematic Symbol
	NPL		Noun, Plural
	NSING		Noun, Singular
	NN		Number
	NP		Noun Phrase
	OH		Oh Interjection ( حرف ندا)
	OHH		Oh noun (منادی)
	P		Preposition
	PP		Prepositional Phrase
	PRO		Pronoun 
	PS		Psedo-Sentence
	QUA		Quantifier
	SPEC		Specifier
	VAUX		Verb, Auxiliary
	VIMP		Verb, Imperative
	VPA		Verb, Past Tense
	VPRE		Verb, Predicative
	VPRS		Verb, Present Tense
	VSUB		Verb, Subjunctive

# Evaluation 
This is my first stab at it. No real evaluation is performed on the model. It is possible to break down the model into 85% and 15% portions and train using the 85% file and evaluate with the unseen data using the 15% file. However, a quick subjective look at the sample output shows that the model has some errors w.r.t real news data. Consider the test output:

(باراک,NSING)(اوباما،,P)(رئیس‌جمهوری,NSING)(آمریکا,NSING)(از,P)(تغییراتی,NSING)(در,P)(شیوه,NSING)(جمع‌آوری,NSING)(اطلاعات,NPL)(و,CON)
(شنود,VPA)(تلفن‌ها,NPL)(توسط,P)(سازمان‌های,NSING)(امنیتی,NSING)(این,NSING)(کشور,NSING)(خبر,NSING)(داده,ADJINO)(است,VPRE)(.,DELM)

To begin with, اوباما is not a proposition. Most probably, the model picked it up as a proposition because of با or او. So that was interesting. THANKS OBAMA! Other than that, I have to refresh my Farsi grammar, but I also don't think that تغییراتی is NSING. Yet the model picked اطلاعات as plural noun which is very similar to تغییراتی. I think ی نکره is taken into account - so really don't know if that should be plural or singular.

Anyhow, This is a good start for now. Any suggestion and/or corrections are welcomed.

Cheers!
هیچ هکر
